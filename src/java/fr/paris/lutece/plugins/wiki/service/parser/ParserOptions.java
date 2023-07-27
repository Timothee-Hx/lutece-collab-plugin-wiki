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
package fr.paris.lutece.plugins.wiki.service.parser;

/**
 * ParserOptions
 */
public class ParserOptions
{

    // Variables declarations

    private String _strTableClass;
    private String _strParentTableClass;
    private String _strImageClass;
    private String _strSizedImageClass;
    private String _strTocClass;

    /**
     * Returns the TableClass
     *
     * @return The TableClass
     */
    public String getTableClass( )
    {
        return _strTableClass;
    }

    /**
     * Sets the TableClass
     *
     * @param strTableClass
     *            The TableClass
     */
    public void setTableClass( String strTableClass )
    {
        _strTableClass = strTableClass;
    }

    /**
     * Returns the ParentTableClass
     *
     * @return The TableClass
     */
    public String getParentTableClass( )
    {
        return _strParentTableClass;
    }

    /**
     * Sets the TableClass
     *
     * @param strParentTableClass
     *            The TableClass
     */
    public void setParentTableClass( String strParentTableClass )
    {
        _strParentTableClass = strParentTableClass;
    }

    /**
     * Returns the ImageClass
     *
     * @return The ImageClass
     */
    public String getImageClass( )
    {
        return _strImageClass;
    }

    /**
     * Sets the ImageClass
     *
     * @param strImageClass
     *            The ImageClass
     */
    public void setImageClass( String strImageClass )
    {
        _strImageClass = strImageClass;
    }

    /**
     * Returns the SizedImageClass
     *
     * @return The SizedImageClass
     */
    public String getSizedImageClass( )
    {
        return _strSizedImageClass;
    }

    /**
     * Sets the SizedImageClass
     *
     * @param strSizedImageClass
     *            The SizedImageClass
     */
    public void setSizedImageClass( String strSizedImageClass )
    {
        _strSizedImageClass = strSizedImageClass;
    }

    /**
     * Returns the TocClass
     *
     * @return The TocClass
     */
    public String getTocClass( )
    {
        return _strTocClass;
    }

    /**
     * Sets the TocClass
     *
     * @param strTocClass
     *            The TocClass
     */
    public void setTocClass( String strTocClass )
    {
        _strTocClass = strTocClass;
    }
}
