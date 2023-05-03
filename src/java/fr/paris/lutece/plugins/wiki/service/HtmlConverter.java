package fr.paris.lutece.plugins.wiki.service;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class HtmlConverter {

    public static String convertMarkdownToHtml(String contentMarkdown) {
        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse(contentMarkdown);
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        return html;
    }
    public static String renderWiki( String strSource )
    {
        String strRender = strSource;
        strRender = strRender.replaceAll( "\\[lt;", "<" );
        strRender = strRender.replaceAll( "\\[gt;", ">" );
        strRender = strRender.replaceAll( "\\[nbsp;", "&nbsp;" );
        strRender = strRender.replaceAll( "\\[quot;", "''" );
        strRender = strRender.replaceAll( "\\[amp;", "&" );
        strRender = strRender.replaceAll( "\\[hashmark;", "#" );
        strRender = strRender.replaceAll("\\[codeQuote;", "`");
        strRender = strRender.replaceAll("\\[simpleQuote;", "'");

        return strRender;
    }
}