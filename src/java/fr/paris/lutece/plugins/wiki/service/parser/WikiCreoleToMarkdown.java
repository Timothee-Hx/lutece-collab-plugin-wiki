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
    public static String wikiCreoleToMd(String strWikiText, String strPageName, String strPageUrl, String strLanguage ) {
// new css class to add in context
        ParserOptions parserOptions = new ParserOptions();
       // parserOptions.setTocClass("wikiCreole_html_toc_class"); ne marche pas
        parserOptions.setSizedImageClass("wikiCreole_html_imageSize_class");
        String htmlContent = new LuteceWikiParser(  strWikiText,  strPageName,  strPageUrl,  strLanguage  ).toString( );


        // parse input HTML
        Document htmlDocument = Jsoup.parse(htmlContent);
        // list all the classes in the html
        List<Element> elements = htmlDocument.getAllElements();
        for (Element element : elements) {
            System.out.println("element.class: " + element.className());
            if(element.className().equals("well")){
                element.remove();
                // add a new element at the beginning of the document
                // markdown to add
                String toc = "<div>\n";
                 toc += "$$\n";
                toc += "<span class ='toc'>Table des matières</span>\n";
                toc += "\n$$\n";
                toc += "</div>\n";
                htmlDocument.prepend(toc);
            }


        }
        System.out.println("htmlContent____________________________\n" + htmlDocument.toString());


        MutableDataSet options = new MutableDataSet();
        options.set(HtmlRenderer.SOFT_BREAK, "\n");
                String markdown = FlexmarkHtmlConverter.builder(options).build().convert(htmlContent);



        return markdown;
    }
}
