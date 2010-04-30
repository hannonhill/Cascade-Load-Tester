/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of Cascade Server asset ids created to
 * run a test that need to be cleaned up.
 *
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class TestAssets
{
    private List<String> pages = new ArrayList<String>();
    private List<String> users = new ArrayList<String>();
    private List<String> configFiles = new ArrayList<String>();
    
    /**
     * Adds the given id to the list of created pages.
     * 
     * @param id
     */
    public void addPage(String id)
    {
        pages.add(id);
    }
    
    /**
     * Adds the given id to the list of created users.
     * @param id
     */
    public void addUser(String id)
    {
        users.add(id);
    }
    
    /**
     * Adds the given file name to the list of created config files.
     * @param fileName
     */
    public void addConfigFile(String fileName)
    {
        configFiles.add(fileName);
    }
    
    /**
     * Returns all the page ids in the list. 
     * @return
     */
    public String[] getPages()
    {
        return pages.toArray(new String[0]);
    }
    
    /**
     * Returns all the user ids in the list.
     * @return
     */
    public String[] getUsers()
    {
        return users.toArray(new String[0]);
    }
    
    /**
     * Returns all the file names in the config file list.
     * @return
     */
    public String[] getConfigFiles()
    {
        return configFiles.toArray(new String[0]);
    }
    
    /**
     * Removes the user id from the list.
     * @param id
     */
    public void removeUser(String id)
    {
        users.remove(id);
    }
    
    /**
     * Removes the page id from the list.
     * @param id
     */
    public void removePage(String id)
    {
        pages.remove(id);
    }
    
    /**
     * Removes the file name from the list of created config files.
     * @param filename
     */
    public void removeConfigFile(String filename)
    {
        configFiles.remove(filename);
    }
}
