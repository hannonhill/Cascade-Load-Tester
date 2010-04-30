/*
 * Created on Mar 22, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.domain;

/**
 * Contains configuration values needed for running a test.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class TestConfiguration
{
    private String host;
    private int port;
    private String username;
    private String password;
    private String pageID;
    private int numUsers;
    private String groups;
    private boolean cloneAsset;
    
    /**
     * @return Returns the host.
     */
    public String getHost()
    {
        return host;
    }
    /**
     * @param host the host to set
     */
    public void setHost(String host)
    {
        this.host = host;
    }
    /**
     * @return Returns the port.
     */
    public int getPort()
    {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port)
    {
        this.port = port;
    }
    /**
     * @return Returns the username.
     */
    public String getUsername()
    {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    /**
     * @return Returns the password.
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    /**
     * @return Returns the pageID.
     */
    public String getPageID()
    {
        return pageID;
    }
    /**
     * @param pageID the pageID to set
     */
    public void setPageID(String pageID)
    {
        this.pageID = pageID;
    }
    /**
     * @return Returns the numUsers.
     */
    public int getNumUsers()
    {
        return numUsers;
    }
    /**
     * @param numUsers the numUsers to set
     */
    public void setNumUsers(int numUsers)
    {
        this.numUsers = numUsers;
    }
    /**
     * @param groups the groups to set
     */
    public void setGroups(String groups)
    {
        this.groups = groups;
    }
    /**
     * @return Returns the groups.
     */
    public String getGroups()
    {
        return groups;
    }
    /**
     * @param cloneAsset the cloneAsset to set
     */
    public void setCloneAsset(boolean cloneAsset)
    {
        this.cloneAsset = cloneAsset;
    }
    /**
     * @return Returns the cloneAsset.
     */
    public boolean isCloneAsset()
    {
        return cloneAsset;
    }
}
