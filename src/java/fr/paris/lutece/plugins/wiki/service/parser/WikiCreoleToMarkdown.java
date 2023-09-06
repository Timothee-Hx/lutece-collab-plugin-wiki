package fr.paris.lutece.plugins.wiki.service.parser;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
        str = str.replaceAll("badge badge-", "badge badge-badge bg-");
        str = str.replaceAll("label label-", "badge badge-badge bg-");
        str = str.replaceAll("glyphicon glyphicon-warning-sign", "fa fa-exclamation-triangle");
        str = str.replaceAll("glyphicon glyphicon-info-sign", "fa fa-info-circle");
        str = str.replaceAll("glyphicon glyphicon-question-sign", "fa fa-question-circle");
        str = str.replaceAll("glyphicon glyphicon-ok-sign", "fa fa-check-circle");
        str = str.replaceAll("glyphicon glyphicon-remove-sign", "fa fa-times-circle");
        str = str.replaceAll("glyphicon glyphicon-chevron-right", "fa fa-chevron-right");
        str = str.replaceAll("glyphicon glyphicon-chevron-left", "fa fa-chevron-left");
        str = str.replaceAll("glyphicon glyphicon-chevron-up", "fa fa-chevron-up");
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
                toc = SpecialChar.reverseRender(toc);
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
                bootStrap5Jumbotron = SpecialChar.reverseRender(bootStrap5Jumbotron);
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
                        int subDivClassToSkip =  element.parent().children().size();
                        String parent = element.parent().outerHtml();
                        String customElement = SpecialChar.reverseRender(parent.toString());
                        customElement = "$$span" + customElement + "$$";
                        Element p = new Element("p");
                        p.text(customElement);
                        element.parent().replaceWith(p);
                        i = i + subDivClassToSkip-1;
            } else {
                        String customElement =  SpecialChar.reverseRender(element.outerHtml().toString());
                        customElement = "$$span" + customElement + "$$";
                        Element p = new Element("p");
                        p.text(customElement);
                        element.replaceWith(p);
                    }

                    }
            } else if (element.tagName().equals("img")) {
                Boolean imgContainsAttributes = element.hasAttr("class") && !element.className().isEmpty()
                        || element.hasAttr("width") && element.getElementsByAttribute("width").size() > 0
                        || element.hasAttr("height") && element.getElementsByAttribute("height").size() > 0
                        || element.hasAttr("align") && element.getElementsByAttribute("align").size() > 0;
                if(element.parent().tagName().equals("p")){
                    String parent = element.parent().outerHtml();
                    String customElement = SpecialChar.reverseRender(parent.toString());
                    customElement = "$$span" + customElement + "$$";
                    Element p = new Element("p");
                    p.text(customElement);
                    element.parent().replaceWith(p);
                } else if (imgContainsAttributes) {
                    String customElement = SpecialChar.reverseRender(element.outerHtml().toString());
                    customElement = "$$span" + customElement + "$$";
                    Element p = new Element("p");
                    p.text(customElement);
                    element.replaceWith(p);
                }
            }
        }

        MutableDataSet options = new MutableDataSet();

        options.set(HtmlRenderer.HARD_BREAK, "<br />\n");
        options.set(FlexmarkHtmlConverter.BR_AS_PARA_BREAKS, false);
        options.set(FlexmarkHtmlConverter.OUTPUT_ATTRIBUTES_ID, false);
        options.set(FlexmarkHtmlConverter.BR_AS_EXTRA_BLANK_LINES, false);
        String markdown = FlexmarkHtmlConverter.builder(options).build().convert(docBody.toString());
        markdown = renderCustomContent(markdown);
       String newMarkdown = "";

       for(String line : markdown.split(System.lineSeparator())) {
                  if (line.contains("$$span")) {
                      line = line.replace("$$span", "");
                      line = line.replace("$$", "");
                      String reFormatedLine =  System.lineSeparator() + "$$span" + System.lineSeparator() + line + System.lineSeparator() + "$$" + System.lineSeparator();
                      newMarkdown += reFormatedLine;
                  } else {
                      newMarkdown += line + System.lineSeparator();
                  }
              }
        return newMarkdown;
    }

}

