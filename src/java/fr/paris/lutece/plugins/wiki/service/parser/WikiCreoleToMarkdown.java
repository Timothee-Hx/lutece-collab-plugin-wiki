package fr.paris.lutece.plugins.wiki.service.parser;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import fr.paris.lutece.plugins.wiki.service.WikiService;

public class WikiCreoleToMarkdown {
    public static String wikiCreoleToMd(String strWikiText, String strPageName, String strPageUrl, String strLanguage ) {

        String htmlContent = new LuteceWikiParser(  strWikiText,  strPageName,  strPageUrl,  strLanguage  ).toString( );

        System.out.println("htmlContent////////////////////////////" + htmlContent);
        String markdown = FlexmarkHtmlConverter.builder().build().convert(htmlContent);
        return markdown;
    }
}
