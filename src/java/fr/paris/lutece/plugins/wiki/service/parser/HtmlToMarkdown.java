package fr.paris.lutece.plugins.wiki.service.parser;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
public class HtmlToMarkdown {

//https://github.com/vsch/flexmark-java/blob/master/flexmark-java-samples/src/com/vladsch/flexmark/java/samples/HtmlToMarkdownSample.java
//https://github.com/vsch/flexmark-java/blob/master/flexmark-java-samples/src/com/vladsch/flexmark/java/samples/HtmlToMarkdownCustomizedSample.java

   public static void convertToMarkdown(String html){
       String markdown = FlexmarkHtmlConverter.builder().build().convert(html);

       System.out.println("HTML:");
       System.out.println(html);

       System.out.println("\nMarkdown:");
       System.out.println(markdown);
   }
}
