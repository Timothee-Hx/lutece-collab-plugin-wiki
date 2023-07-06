package fr.paris.lutece.plugins.wiki.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class LuteceHtmlParser {



    public static String parseHtml(String htmlFromEditor, String wikiPageUrl, String pageTitle) {
        htmlFromEditor = SpecialChar.renderWiki(htmlFromEditor);
        Document parser = Jsoup.parse(htmlFromEditor);
        Element doc = parser.body();
        List<Element> headers = doc.select("h1, h2, h3, h4, h5, h6");
        for (Element header : headers) {
            String headerText = header.text();
            headerText = headerText.replaceAll(" ", "_");
            header.attr("id", headerText);
        }
        List<Element> preElements = doc.select("pre");
        for (Element preElement : preElements) {
            if (preElement.className().startsWith("lang-")) {
                preElement.attr("style", "background-color: #2f3241");
            }
        }
        doc.select(".toastui-editor-md-preview-highlight").forEach(element -> element.removeClass("toastui-editor-md-preview-highlight"));
        if (doc.select(".ProseMirror").text().contains("wiki-align-content-val-")) {
            String alignmentValue = doc.select(".ProseMirror").text().split("wiki-align-content-val-")[1].substring(0, 1);
            doc.select(".toastui-editor-contents").addClass("wiki-align-content-val-" + alignmentValue);
        }
        Element toc = doc.select(".toc").first();
        if (toc != null) {
            Element tableOfContent = createTableOfContent(doc, wikiPageUrl, pageTitle);
            doc.select(".toc").remove();

            Element flexDiv = new Element("div");
            flexDiv.addClass("flex");
            flexDiv.addClass("flex-column");
            flexDiv.addClass("flex-grow-1");
            flexDiv.addClass("flex-shrink-1");
            flexDiv.addClass("flex-basis-auto");
            flexDiv.addClass("overflow-auto");
            flexDiv.appendChild(tableOfContent);
            Element contentDiv = new Element("div");
            contentDiv.addClass("wiki_content");
            contentDiv.append(parser.body().outerHtml());
            flexDiv.appendChild(contentDiv);
            doc = flexDiv;

            return SpecialChar.reverseRender(doc.outerHtml());
        } else {
            Element contentDiv = new Element("div");
            contentDiv.addClass("wiki_content");
            contentDiv.append(parser.body().outerHtml());
            return SpecialChar.reverseRender(contentDiv.outerHtml());
        }
    }
  public static Element createTableOfContent(Element doc, String wikiPageUrl,String pageTitle) {
        Element tableOfContent = new Element("ul");
        tableOfContent.addClass("nav");
        tableOfContent.addClass("flex-column");
        tableOfContent.addClass("wiki-topic-nav");
        Element titleElement = new Element("a");
        titleElement.addClass("nav-link");
        titleElement.attr("href", wikiPageUrl);
        titleElement.attr("style", "font-weight: bold; font-size: 1.5rem;");
        titleElement.text(pageTitle);
        tableOfContent.appendChild(titleElement);
        List<Element> headers = doc.select("h1, h2, h3");
        for (int i = 0; i < headers.size(); i++) {
            Element header = headers.get(i);
            String headerText = header.text();
            String headerLevel = header.tagName();
            Element linkElement = new Element("a");
            linkElement.attr("href", wikiPageUrl+"#" + header.id());
            linkElement.addClass("nav-link");
            linkElement.text(headerText);
            Element navItem = new Element("li");
            if (headerLevel.equals("h1")) {
                navItem.addClass("nav-item");
                if(i + 1 < headers.size()) {
                    if (headers.get(i + 1).tagName().equals("h1")) {
                        tableOfContent.appendChild(linkElement);
                    } else if (headers.get(i + 1).tagName().equals("h2") || headers.get(i + 1).tagName().equals("h3")) {
                        Element divContainer = new Element("div");
                        divContainer.attr("style", "display: flex; flex-direction: row; spacing: 5px;");
                        divContainer.appendChild(linkElement);
                        navItem.appendChild(divContainer);
                        tableOfContent.appendChild(navItem);
                        Element subLinkContainer = new Element("ul");
                        subLinkContainer.addClass("subLinkContainer");
                        tableOfContent.appendChild(subLinkContainer);
                    } else {
                        tableOfContent.appendChild(linkElement);
                    }
                }
            }
            if (headerLevel.equals("h2") || headerLevel.equals("h3")) {
                List<Element> subLinkContainers = tableOfContent.getElementsByClass("subLinkContainer");
                if(subLinkContainers.size() != 0) {
                    Element subLinkContainer = subLinkContainers.get(subLinkContainers.size() - 1);
                    subLinkContainer.appendChild(linkElement);
                }
            }
        }
        return tableOfContent;
    }


}
