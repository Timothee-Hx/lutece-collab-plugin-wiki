/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.wiki.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import fr.paris.lutece.plugins.wiki.business.Topic;
import fr.paris.lutece.plugins.wiki.business.TopicHome;
import fr.paris.lutece.plugins.wiki.business.TopicVersion;
import fr.paris.lutece.plugins.wiki.business.TopicVersionHome;
import fr.paris.lutece.plugins.wiki.service.parser.LuteceWikiParser;
import fr.paris.lutece.portal.service.content.XPageAppService;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.search.IndexationService;
import fr.paris.lutece.portal.service.search.SearchIndexer;
import fr.paris.lutece.portal.service.search.SearchItem;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.url.UrlItem;


/**
 * Wiki Indexer
 * 
 */
public class WikiIndexer implements SearchIndexer
{
    private static final String PROPERTY_INDEXER_NAME = "wiki.indexer.name";
    private static final String SHORT_NAME_TOPIC = "wis";
    private static final String PARAMETER_PAGE_NAME = "page_name";
    private static final String PLUGIN_NAME = "wiki";
    private static final String PROPERTY_PAGE_BASE_URL = "search.pageIndexer.baseUrl";
    private static final String PROPERTY_DOCUMENT_TYPE = "wiki.indexer.documentType";
    private static final String PROPERTY_INDEXER_DESCRIPTION = "wiki.indexer.description";
    private static final String PROPERTY_INDEXER_VERSION = "wiki.indexer.version";
    private static final String PROPERTY_INDEXER_ENABLE = "wiki.indexer.enable";
    private static final String ENABLE_VALUE_TRUE = "1";
    private static final String JSP_SEARCH_WIKI = "jsp/site/Portal.jsp?page=wiki&action=search";

    /**
     * {@inheritDoc }
     */
    @Override
    public String getDescription( )
    {
        return AppPropertiesService.getProperty( PROPERTY_INDEXER_DESCRIPTION );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Document> getDocuments( String strDocument ) throws IOException, InterruptedException,
            SiteMessageException
    {
        List<org.apache.lucene.document.Document> listDocs = new ArrayList<org.apache.lucene.document.Document>( );
        String strPortalUrl = AppPropertiesService.getProperty( PROPERTY_PAGE_BASE_URL );
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );

        Topic topic = TopicHome.findByPrimaryKey( Integer.parseInt( strDocument ), plugin );

        if ( topic != null )
        {
            UrlItem urlSubject = new UrlItem( strPortalUrl );
            urlSubject.addParameter( XPageAppService.PARAM_XPAGE_APP, PLUGIN_NAME );
            urlSubject.addParameter( PARAMETER_PAGE_NAME, topic.getPageName( ) );

            org.apache.lucene.document.Document docSubject = getDocument( topic, urlSubject.getUrl( ), plugin );
            listDocs.add( docSubject );
        }

        return listDocs;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getName( )
    {
        return AppPropertiesService.getProperty( PROPERTY_INDEXER_NAME );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getVersion( )
    {
        return AppPropertiesService.getProperty( PROPERTY_INDEXER_VERSION );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void indexDocuments( ) throws IOException, InterruptedException, SiteMessageException
    {
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );

        for ( Topic topic : TopicHome.getTopicsList( plugin ) )
        {
            indexTopic( topic );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEnable( )
    {
        boolean bReturn = false;
        String strEnable = AppPropertiesService.getProperty( PROPERTY_INDEXER_ENABLE );

        if ( ( strEnable != null )
                && ( strEnable.equalsIgnoreCase( Boolean.TRUE.toString( ) ) || strEnable.equals( ENABLE_VALUE_TRUE ) )
                && PluginService.isPluginEnable( PLUGIN_NAME ) )
        {
            bReturn = true;
        }

        return bReturn;
    }

    /**
     * Indexe the topic
     * @param topic The topic
     * @throws IOException if an IO error occurs
     * @throws InterruptedException if a Thread error occurs
     */
    public void indexTopic( Topic topic ) throws IOException, InterruptedException
    {
        String strPortalUrl = AppPropertiesService.getProperty( PROPERTY_PAGE_BASE_URL );
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );

        UrlItem urlSubject = new UrlItem( strPortalUrl );
        urlSubject.addParameter( XPageAppService.PARAM_XPAGE_APP, PLUGIN_NAME );
        urlSubject.addParameter( PARAMETER_PAGE_NAME, topic.getPageName( ) );

        org.apache.lucene.document.Document docTopic = null;

        try
        {
            docTopic = getDocument( topic, urlSubject.getUrl( ), plugin );
        }
        catch ( Exception e )
        {
            String strMessage = "Topic ID : " + topic.getIdTopic( );
            IndexationService.error( this, e, strMessage );
        }

        if ( docTopic != null )
        {
            IndexationService.write( docTopic );
        }
    }

    /**
     * Get a document for indexing
     * @param topic The topic
     * @param strUrl The URL
     * @param plugin The plugin
     * @return The document
     * @throws IOException if an IO error occurs
     * @throws InterruptedException if a Thread error occurs
     */
    public static org.apache.lucene.document.Document getDocument( Topic topic, String strUrl, Plugin plugin )
            throws IOException, InterruptedException
    {
        // make a new, empty document
        org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document( );

        FieldType ft = new FieldType( StringField.TYPE_STORED );
        ft.setOmitNorms( false );

        FieldType ftNotStored = new FieldType( StringField.TYPE_NOT_STORED );
        ftNotStored.setOmitNorms( false );
        ftNotStored.setTokenized( false );
        // Add the url as a field named "url". Use an UnIndexed field, so
        // that the url is just stored with the question/answer, but is not
        // searchable.
        doc.add( new Field( SearchItem.FIELD_URL, strUrl, ft ) );

        // Add the uid as a field, so that index can be incrementally
        // maintained.
        // This field is not stored with question/answer, it is indexed, but it
        // is not
        // tokenized prior to indexing.
        String strIdSubject = String.valueOf( topic.getPageName( ) );
        doc.add( new Field( SearchItem.FIELD_UID, strIdSubject + "_" + SHORT_NAME_TOPIC, ftNotStored ) );

        TopicVersion latestTopicVersion = TopicVersionHome.findLastVersion( topic.getIdTopic( ), plugin );
        String strWikiContent = "";

        if ( ( latestTopicVersion != null ) && ( latestTopicVersion.getWikiContent( ) != null )
                && !latestTopicVersion.getWikiContent( ).equals( "" ) )
        {
            strWikiContent = latestTopicVersion.getWikiContent( );
        }

        String strWikiResult = new LuteceWikiParser( strWikiContent ).toString( ) + " " + topic.getPageName( );
        doc.add( new Field( SearchItem.FIELD_CONTENTS, strWikiResult, TextField.TYPE_NOT_STORED ) );

        String strDate = DateTools.dateToString( latestTopicVersion.getDateEdition( ), DateTools.Resolution.DAY );
        doc.add( new Field( SearchItem.FIELD_DATE, strDate, ft ) );

        // Add the subject name as a separate Text field, so that it can be
        // searched separately.
        doc.add( new Field( SearchItem.FIELD_TITLE, topic.getPageName( ), ft ) );

        doc.add( new Field( SearchItem.FIELD_TYPE, getDocumentType( ), ft ) );

        doc.add( new Field( SearchItem.FIELD_ROLE, topic.getRole( ), ft ) );

        return doc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getListType( )
    {
        List<String> listType = new ArrayList<String>( );
        listType.add( getDocumentType( ) );

        return listType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSpecificSearchAppUrl( )
    {
        return JSP_SEARCH_WIKI;
    }

    /**
     * Get Lucene index document type
     * @return The document type
     */
    public static String getDocumentType( )
    {
        return AppPropertiesService.getProperty( PROPERTY_DOCUMENT_TYPE );
    }
}
