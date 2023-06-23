package fr.paris.lutece.plugins.wiki.service.parser;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.MutableDataSet;
//import an html parser
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;


public class WikiCreoleToMarkdown {

    public static String renderCustomContent ( String str){
        str = str.replaceAll("\\\\", "");

        String customStart = "$$span";
        String customEnd =   "$$" ;
        str = str.replaceAll( "\\[lt;", "<" );
        str = str.replaceAll( "\\[gt;", ">" );
        str = str.replaceAll( "\\[nbsp;", "&nbsp;" );
        str = str.replaceAll( "\\[quot;", "'" );
        str = str.replaceAll( "\\[amp;", "&" );
        str = str.replaceAll( "\\[hashmark;", "#" );
        str = str.replaceAll("\\[codeQuote;", "`");
        str = str.replaceAll("\\[simpleQuote;", "'");
        str = customStart + str + customEnd;
        // if there is two $$span, remove one of them
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        while ((i = sb.indexOf(customStart, i)) != -1) {
            sb.delete(i, i + customStart.length());
            i++;
        }


        str = sb.toString();
        System.out.println("####################################str: " + str);

        // remove all backslash
        return str;
    }

    public static String wikiCreoleToMd(String strWikiText, String strPageName, String strPageUrl, String strLanguage ) {

        String htmlContent = new LuteceWikiParser(  strWikiText,  strPageName,  strPageUrl,  strLanguage  ).toString( );


        Document htmlDocument =  Jsoup.parse(htmlContent);
          Element docBody = htmlDocument.body();
        List<Element> elements = docBody.getAllElements();
        for (Element element : elements) {


            System.out.println("element.class: " + element.className());
            if(element.className().equals("well")){
                String toc = "<span class=toc></span>";
                toc = LuteceWikiParser.reverseRender(toc);
                docBody.prepend(toc);
            } else if (element.className().equals("jumbotron")) {
                /*
                   let bootStrap5Jumbotron = '<div class="h-100 p-5 text-bg-dark rounded-3">\n' +
                '          <h2 class="text-white">' + jumbotronTitle + '</h2>\n' +
                '          <p class="text-light">' + jumbotronText + '</p>\n' +
                '        </div>';
        editor.insertText("$$span\n"+bootStrap5Jumbotron+ "\n$$");
                 */
          /*      Element jumbotron = element;
                String jumbotronTitle = jumbotron.select("h1").text();
                String jumbotronText = jumbotron.select("p").text();
                String bootStrap5Jumbotron = "<p>$$span</p>";
                bootStrap5Jumbotron += "<p>tagNameWikiConvertStart div classNameWikiConvertStart h-100 p-5 text-bg-dark rounded-3 classNameWikiConvertEnd tagNameWikiConvertEnd\n";
          */
           } else if (!element.className().isEmpty()) {

                if(!element.tagName().equals("table")){
                    System.out.println("__________FIRST_________element.class: " + element.toString());
                List<Element> elChildren = element.children();

                    for(Element elChild : elChildren){

                            System.out.println("_________________CHILDREN____________elChild.class: " + elChild.className());
                        }
                    }
                }
            }

        System.out.println("htmlContent____________________________\n" + docBody.toString());
        // remove tags <html> <head> and <body> and keep the inner of the body
       htmlContent = docBody.toString();

        MutableDataSet options = new MutableDataSet();


        options.set(FlexmarkHtmlConverter.SKIP_FENCED_CODE, true);

        options.set(FlexmarkHtmlConverter.BR_AS_PARA_BREAKS, true);
        options.set(FlexmarkHtmlConverter.OUTPUT_ATTRIBUTES_ID, false);
        options.set(FlexmarkHtmlConverter.BR_AS_EXTRA_BLANK_LINES, false);

                String markdown = FlexmarkHtmlConverter.builder(options).build().convert(htmlContent);
                markdown = renderCustomContent(markdown);
                // find all tagNameWikiConvertion and replace by the tagName

        System.out.println("________________________MARKDOWN_________________\n" + markdown.toString());


        return markdown;
    }
}
