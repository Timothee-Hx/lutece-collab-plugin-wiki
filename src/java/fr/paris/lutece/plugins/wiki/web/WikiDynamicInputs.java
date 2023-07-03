/*
 * Copyright (c) 2002-2021, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.wiki.web;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paris.lutece.api.user.User;
import fr.paris.lutece.plugins.wiki.business.*;
import fr.paris.lutece.plugins.wiki.service.ContentDeserializer;
import fr.paris.lutece.plugins.wiki.service.RoleService;
import fr.paris.lutece.plugins.wiki.service.parser.SpecialChar;
import fr.paris.lutece.plugins.wiki.utils.auth.WikiAnonymousUser;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.plugins.wiki.service.parser.LuteceHtmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Upload application
 */
public class WikiDynamicInputs {


    public static String saveWiki(HttpServletRequest request) throws IOException, UserNotSignedException {


        Boolean saveSuccess = false;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        try {
            ContentDeserializer newContent = ContentDeserializer.deserializeWikiContent(requestBody);
            LuteceUser user = WikiAnonymousUser.checkUser(request);

            Topic topic = TopicHome.findByPrimaryKey(newContent.getTopicId());
            if (RoleService.hasEditRole(request, topic)) {
                int topicVersionId = newContent.getTopicVersion();

                int nTopicId = topic.getIdTopic();
                TopicVersion topicVersion = TopicVersionHome.findByPrimaryKey(topicVersionId);
                topicVersion.setIdTopic(nTopicId);
                topicVersion.setIdTopicVersion(topicVersionId);
                topicVersion.setUserName(user.getName());
                if (topicVersion.getEditComment() == null || topicVersion.getEditComment().isEmpty()) {
                    topicVersion.setEditComment("AutoSave");
                }
                topicVersion.setEditComment(topicVersion.getEditComment());
                topicVersion.setIsPublished(false);
                topicVersion.setLuteceUserId(user.getFirstName() + "-" + user.getName());
                WikiContent content = new WikiContent();
                content.setPageTitle(newContent.getTopicTitle());
                content.setWikiContent(newContent.getTopicContent());
                content.setContentLabellingMarkdownLanguage(newContent.getTopicContent());
                String wikiPageUrl = newContent.getWikiPageUrl();
                String htmlContent = LuteceHtmlParser.parseHtml(newContent.getTopicContent(), wikiPageUrl, newContent.getTopicTitle());
                content.setHtmlWikiContent(htmlContent);
                topicVersion.addLocalizedWikiContent(newContent.getLanguage(), content);
                TopicVersionHome.updateTopicVersion(topicVersion);
                topic.setViewRole(topic.getViewRole());
                topic.setEditRole(topic.getEditRole());
                topic.setParentPageName(newContent.getParentPageName());
                TopicHome.update(topic);
                saveSuccess = true;
            } else {
                throw new UserNotSignedException();
            }
        } catch (Exception e) {
            AppLogService.error("Error saving topic version automatically", e);
        }
        // return the response in json
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(saveSuccess);
    }

    public static void updateLastOpenModifyTopicPage(HttpServletRequest request) throws IOException, UserNotSignedException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final int topicId = gson.fromJson(requestBody, int.class);
        Topic topic = TopicHome.findByPrimaryKey(topicId);
        try {
            if (RoleService.hasEditRole(request, topic)) {
                User user = WikiAnonymousUser.checkUser(request);
                TopicHome.updateLastOpenModifyPage(topic.getIdTopic(), user);
            } else {
                throw new UserNotSignedException();
            }
        } catch (Exception e) {
            AppLogService.error("Error saving last user opening modify topic page", e);

        }
    }

    public static HttpServletResponse getPageHeadings(HttpServletRequest resquest, HttpServletResponse response) throws IOException, UserNotSignedException {
        String pageName = resquest.getParameter("pageName");
        String locale = resquest.getParameter("locale");
        Topic topic = TopicHome.findByPageName(pageName);
        GsonBuilder builder = new GsonBuilder();
        Gson gsonHeadings = builder.create();

        List < HashMap < String, String >> headings = new ArrayList< HashMap < String, String >>();
        try {
            if (RoleService.hasEditRole(resquest, topic)) {
                // get published version
                TopicVersion topicVersion = TopicVersionHome.getPublishedVersion(topic.getIdTopic());
                WikiContent wikiContent = topicVersion.getWikiContent(locale);
                String htmlContent = SpecialChar.renderWiki(wikiContent.getHtmlWikiContent());
                // find class="wiki_content' and if it exists, get the headings
                if (htmlContent.contains("class=\"wiki_content\"")) {
                    Document htmlDocument = Jsoup.parse(htmlContent);
                    System.out.println(htmlDocument);
                    Element docBody = htmlDocument.body();
                    Element wikiContentElement = docBody.getElementsByClass("wiki_content").first();
                    for (Element element : wikiContentElement.select("h1, h2, h3, h4, h5, h6")) {
                        HashMap < String, String > heading = new HashMap < String, String > ();
                        heading.put("header_id", element.id());
                        heading.put("header_text", element.text());
                        headings.add(heading);
                    }
                } else {
                    // this means the page is from a previous version and the wiki_content class is not present
                    Document htmlDocument = Jsoup.parse(htmlContent);
                    Element docBody = htmlDocument.body();
                    for (Element element : docBody.select("h1, h2, h3, h4, h5, h6")) {
                        HashMap < String, String > heading = new HashMap < String, String > ();
                        heading.put("header_id", element.id());
                        heading.put("header_text", element.text());
                        headings.add(heading);
                    }
                }
            } else {
                throw new UserNotSignedException();
            }
        } catch (Exception e) {
            AppLogService.error("Error saving last user opening modify topic page", e);

        }
        String res = gsonHeadings.toJson(headings).toString();
        response.getWriter().write(res);
        return response;
    }
}