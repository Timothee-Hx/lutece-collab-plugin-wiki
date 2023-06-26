package fr.paris.lutece.plugins.wiki.service.parser;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.MutableDataSet;
//import an html parser
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.Arrays;
import java.util.List;


public class WikiCreoleToMarkdown {

    public static String renderCustomContent ( String str){
        str = str.replaceAll("\\\\", "");


        str = str.replaceAll( "\\[lt;", "<" );
        str = str.replaceAll( "\\[gt;", ">" );
        str = str.replaceAll( "\\[nbsp;", "&nbsp;" );
        str = str.replaceAll( "\\[quot;", "'" );
        str = str.replaceAll( "\\[amp;", "&" );
        str = str.replaceAll( "\\[hashmark;", "#" );
        str = str.replaceAll("\\[codeQuote;", "`");
        str = str.replaceAll("\\[simpleQuote;", "'");
        return str;
    }

    public static String wikiCreoleToMd(String strWikiText, String strPageName, String strPageUrl, String strLanguage ) {

        String htmlContent = new LuteceWikiParser(  strWikiText,  strPageName,  strPageUrl,  strLanguage  ).toString( );

        Document htmlDocument =  Jsoup.parse(htmlContent);
          Element docBody = htmlDocument.body();
        List<Element> elements = docBody.getAllElements();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            if(element.className().equals("well")){
                String toc = "$$span"+" "+"<span class='toc'></span>"+ " "+ "$$";
                toc = LuteceWikiParser.reverseRender(toc);
                docBody.prepend(toc);

            } else if (element.className().equals("jumbotron")) {
                Element jumbotron = element;
                String jumbotronTitle = jumbotron.select("h1").text();
                String jumbotronText = jumbotron.select("p").text();
                Element container = new Element("span");
                container.attr("class", "h-100 p-5 text-bg-light rounded-3");
                container.attr("style", "display: block;");
                if(jumbotron.select("img").size() > 0){
                    Element img = jumbotron.select("img").first();
                    Element figure = new Element("figure");
                    figure.attr("class", "figure");
                    figure.appendChild(img);
                    container.appendChild(figure);
                }
                container.appendChild(new Element("h1").attr("class", "text-dark").text(jumbotronTitle));
                container.appendChild(new Element("p").attr("class", "text-muted").text(jumbotronText));
                String bootStrap5Jumbotron = "$$span\n" + container.toString() + "\n$$";
                bootStrap5Jumbotron = LuteceWikiParser.reverseRender(bootStrap5Jumbotron);
                Element p = new Element("p");
                p.text(bootStrap5Jumbotron);
                jumbotron.replaceWith(p);

           } else if (!element.className().isEmpty()) {
             String[] classNamesToSkip = {"null", "table", "tbody", "thead", "tr", "td", "th"};
                if(Arrays.asList(classNamesToSkip).contains(element.className())){
                   i++;
                }
               else {
                    if(element.parent().tagName().equals("p")){
                        System.out.println(i);
                        int subDivClassToSkip =  element.parent().children().size();
                        System.out.println("elements.size() " + elements.size());
                        String parent = element.parent().outerHtml();
                        String customElement = LuteceWikiParser.reverseRender(parent.toString());
                        customElement = "$$span" + customElement + "$$";
                        Element p = new Element("p");
                        p.text(customElement);
                        element.parent().replaceWith(p);
                        i = i + subDivClassToSkip-1;
            } else {
                        String customElement =  LuteceWikiParser.reverseRender(element.outerHtml().toString());
                        customElement = "$$span" + customElement + "$$";
                        Element p = new Element("p");
                        p.text(customElement);
                        element.replaceWith(p);
                    }

                    }
            }
        }

        // remove tags <html> <head> and <body> and keep the inner of the body
       htmlContent = docBody.toString();


        MutableDataSet options = new MutableDataSet();


        options.set(HtmlRenderer.HARD_BREAK, "<br />\n");
        options.set(FlexmarkHtmlConverter.BR_AS_PARA_BREAKS, false);
        options.set(FlexmarkHtmlConverter.OUTPUT_ATTRIBUTES_ID, false);
        options.set(FlexmarkHtmlConverter.BR_AS_EXTRA_BLANK_LINES, false);
        String markdown = FlexmarkHtmlConverter.builder(options).build().convert(htmlContent);
        markdown = renderCustomContent(markdown);
       String newMarkdown = "";


       for(String line : markdown.split(System.lineSeparator())) {
                  if (line.contains("$$span")) {
                      // remove "$$" adn "$$span" in the line
                      line = line.replace("$$span", "");
                      line = line.replace("$$", "");

                      String reFormatedLine =  System.lineSeparator() + "$$span" + System.lineSeparator() + line + System.lineSeparator() + "$$" + System.lineSeparator();
                      newMarkdown += reFormatedLine;

                      // add a new line after before and after line
                  } else {
                      newMarkdown += line + System.lineSeparator();
                  }
              }


        System.out.println("________________________MARKDOWN_________________\n" + newMarkdown);


        return newMarkdown;
    }

}

