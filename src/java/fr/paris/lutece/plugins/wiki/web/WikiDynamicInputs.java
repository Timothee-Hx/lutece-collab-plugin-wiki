/*
 * Copyright (c) 2002-2023, City of Paris
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
import fr.paris.lutece.plugins.wiki.service.WikiLocaleService;
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
public class WikiDynamicInputs
{
    public static String autoSaveWiki( HttpServletRequest request ) throws IOException, UserNotSignedException
    {
        Boolean saveSuccess = false;
        StringBuilder sb = new StringBuilder( );
        BufferedReader reader = request.getReader( );
        String line;
        while ( ( line = reader.readLine( ) ) != null )
        {
            sb.append( line );
        }
        String requestBody = sb.toString( );

        try
        {
            ContentDeserializer newContent = ContentDeserializer.deserializeWikiContent( requestBody );
            LuteceUser user = WikiAnonymousUser.checkUser( request );

            Topic topic = TopicHome.findByPrimaryKey( newContent.getTopicId( ) );
            if ( RoleService.hasEditRole( request, topic ) )
            {
                int topicVersionId = newContent.getTopicVersion( );
                int nTopicId = topic.getIdTopic( );
                TopicVersion topicVersion = TopicVersionHome.findByPrimaryKey( topicVersionId );
                topicVersion.setIdTopic( nTopicId );
                topicVersion.setIdTopicVersion( topicVersionId );
                topicVersion.setUserName( user.getName( ) );
                if ( topicVersion.getEditComment( ) == null || topicVersion.getEditComment( ).isEmpty( ) )
                {
                    topicVersion.setEditComment( "AutoSave" );
                }
                topicVersion.setEditComment( topicVersion.getEditComment( ) );
                topicVersion.setIsPublished( false );
                topicVersion.setLuteceUserId( user.getFirstName( ) + "-" + user.getName( ) );
                WikiContent content = new WikiContent( );
                content.setPageTitle( newContent.getTopicTitle( ) );
                content.setWikiContent( newContent.getTopicContent( ) );
                content.setContentLabellingMarkdownLanguage( newContent.getTopicContent( ) );
                String wikiPageUrl = newContent.getWikiPageUrl( );
                String htmlContent = LuteceHtmlParser.parseHtml( newContent.getWikiHtmlContent( ), wikiPageUrl, newContent.getTopicTitle( ) );
                content.setHtmlWikiContent( htmlContent );
                topicVersion.addLocalizedWikiContent( newContent.getLanguage( ), content );
                TopicVersionHome.updateTopicVersion( topicVersion );
                topic.setViewRole( topic.getViewRole( ) );
                topic.setEditRole( topic.getEditRole( ) );
                topic.setParentPageName( newContent.getParentPageName( ) );
                TopicHome.update( topic );
                saveSuccess = true;
            }
            else
            {
                throw new UserNotSignedException( );
            }
        }
        catch( Exception e )
        {
            AppLogService.error( "Error saving topic version automatically", e );
        }
        // return the response in json
        GsonBuilder builder = new GsonBuilder( );
        Gson gson = builder.create( );
        return gson.toJson( saveSuccess );
    }


    public static HttpServletResponse modifyPage( HttpServletRequest request, HttpServletResponse response ) throws IOException, UserNotSignedException
    {
        StringBuilder sb = new StringBuilder( );
        BufferedReader reader = request.getReader( );
        String line;
        while ( ( line = reader.readLine( ) ) != null )
        {
            sb.append( line );
        }
        String requestBody = sb.toString( );
        Boolean publish = false;
        Boolean newVersion = false;
        String wikiPageUrl = "";

        try
        {
            ContentDeserializer newContent = ContentDeserializer.deserializeWikiContent( requestBody );
            Topic topic = TopicHome.findByPageName( newContent.getTopicPageName( ) );
            LuteceUser user = WikiAnonymousUser.checkUser( request );

            if ( RoleService.hasEditRole( request, topic ) )
            {

                Integer nVersionId = newContent.getTopicVersion( );
                newVersion = newContent.getIsCreateVersion( );
                publish = newContent.getIsPublishVersion( );

                int nTopicId = topic.getIdTopic( );
                String strComment = newContent.getEditComment( );
                String strLocale = newContent.getLanguage( );
                wikiPageUrl = newContent.getWikiPageUrl( );
                String strParentPageName = newContent.getParentPageName( );
                String strViewRole = newContent.getViewRole( );
                String strEditRole = newContent.getEditRole( );
                String strPageTitle = newContent.getTopicTitle( );
                String strContent = newContent.getTopicContent( );
                String htmlContent = newContent.getWikiHtmlContent( );
                TopicVersion topicVersion = new TopicVersion( );

                if ( nVersionId != 0 )
                {
                    topicVersion = TopicVersionHome.findByPrimaryKey( nVersionId );
                }
                topicVersion.setUserName( user.getName( ) );
                topicVersion.setIdTopic( nTopicId );
                topicVersion.setUserName( user.getName( ) );
                topicVersion.setEditComment( strComment );
                topicVersion.setIsPublished( publish );

                htmlContent = LuteceHtmlParser.parseHtml( htmlContent, wikiPageUrl, strPageTitle );

                WikiContent content = new WikiContent( );
                if ( nVersionId == 0 )
                {
                    for ( String locale : WikiLocaleService.getLanguages( ) )
                    {
                        content.setPageTitle( strPageTitle + "_" + locale );
                        content.setContentLabellingMarkdownLanguage( strContent );
                        content.setHtmlWikiContent( htmlContent );
                        topicVersion.addLocalizedWikiContent( locale, content );
                    }
                }
                content.setPageTitle( strPageTitle );
                content.setContentLabellingMarkdownLanguage( strContent );
                content.setHtmlWikiContent( htmlContent );
                topicVersion.addLocalizedWikiContent( strLocale, content );
                // if publish is true, cancel publication of previous version
                if ( publish.equals( true ) )
                {
                    String comment = "publication canceled by" + " " + user.getName( );
                    TopicVersionHome.cancelPublication( nTopicId, comment );
                }
                // if newVersion is true or publish is true, create a new version
                if ( newVersion || publish.equals( true ) )
                {
                    TopicVersionHome.addTopicVersion( topicVersion );
                    topic.setViewRole( strViewRole );
                    topic.setEditRole( strEditRole );
                    topic.setParentPageName( strParentPageName );
                    TopicHome.update( topic );
                }
            }
        }
        catch( Exception e )
        {
            AppLogService.error( "Error saving topic version automatically", e );
        }
        GsonBuilder builder = new GsonBuilder( );
        Gson resToJson = builder.create( );
        HashMap<String, String> result = new HashMap<String, String>( );
        if ( publish.equals( true ) )
        {
            result.put( "action", "publish" );
            result.put( "url", SpecialChar.renderWiki( wikiPageUrl ) );
            String res = resToJson.toJson( result );
            response.getWriter( ).write( res );
        }
        else
            if ( newVersion && !publish.equals( true ) )
            {
                result.put( "action", "savedInNewVersion" );
                result.put( "url", SpecialChar.renderWiki( wikiPageUrl ) );
                String res = resToJson.toJson( result );
                response.getWriter( ).write( res );
            }
        return response;
    }

}
