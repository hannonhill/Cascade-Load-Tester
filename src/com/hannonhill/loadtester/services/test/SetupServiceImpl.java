/*
 * Created on Mar 29, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.services.PropertiesService;
import com.hannonhill.loadtester.utils.ConfigFileUtil;
import com.hannonhill.loadtester.utils.MessageBuffer;
import com.hannonhill.loadtester.webservices.PageUtil;
import com.hannonhill.loadtester.webservices.UserUtil;
import com.hannonhill.www.ws.ns.AssetOperationService.Authentication;

/**
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class SetupServiceImpl implements SetupService
{
    private Logger logger = Logger.getLogger(SetupService.class);
    @Autowired
    private PropertiesService propService;

    /* (non-Javadoc)
     * @see com.hannonhill.loadtester.test.SetupService#setupForTest(com.hannonhill.loadtester.domain.TestConfiguration)
     */
    @Override
    public void setupForTest(TestConfiguration config, MessageBuffer messageBuffer, TestAssets testAssets) 
    {
        logger.info("Setting up assets to run test");
        messageBuffer.addMessage("Setting up assets needed for test");
        Authentication auth = new Authentication();
        auth.setUsername(config.getUsername());
        auth.setPassword(config.getPassword());
        createUsers(config, auth, messageBuffer, testAssets);
        if(config.isCloneAsset())
        {
            createPages(config, auth, messageBuffer, testAssets);
        }
        createConfigFiles(config, messageBuffer, testAssets);
        messageBuffer.addMessage("Setup complete!"); 
    }
    
    /**
     * Creates a number of users on the cascade server instance based on the TestConfiguration and
     * returns a list of the ids of the created users 
     * @param config
     * @param auth
     * @return List of created user ids
     */
    private List<String> createUsers(TestConfiguration config, Authentication auth, MessageBuffer messageBuffer, TestAssets testAssets)
    {
        logger.info("Creating test users");
        messageBuffer.addMessage("Creating test users...");
        UserUtil userUtil = new UserUtil(config.getHost(), config.getPort(), auth);
        List<String> createdUserList = new ArrayList<String>();
        for(int i=1; i<= config.getNumUsers(); i++)
        {
            logger.debug("Creating user: " + propService.getProperties().getProperty("test.username") + i);
            String id = propService.getProperties().getProperty("test.username") + i;
            userUtil.createUser(id, config.getGroups());
            testAssets.addUser(id);
            logger.debug("Created user with id: " + id);
            createdUserList.add(id);
        }
        messageBuffer.addMessage("Created "+createdUserList.size()+" test users.");
        return createdUserList;
    }
    
    /**
     * Creates a test page for each user that is created and returns a list of the ids of the pages that were created.
     * 
     * @param config
     * @param auth
     * @return List of created page ids
     */
    private List<String> createPages(TestConfiguration config, Authentication auth, MessageBuffer messageBuffer, TestAssets testAssets)
    {
        logger.info("Creating test pages");
        messageBuffer.addMessage("Creating test pages...");
        PageUtil pageUtil = new PageUtil(config.getHost(), config.getPort(), auth);
        List<String> createdPageList = new ArrayList<String>();
        for(int i=1; i<=config.getNumUsers();i++)
        {
            logger.debug("Creating page: " + i + " of " + config.getNumUsers());
            String id = pageUtil.createPage(config.getPageID(), propService.getProperties().getProperty("test.pagename"));
            testAssets.addPage(id);
            logger.debug("Created page with id: " + id);
            createdPageList.add(id);
        }
        messageBuffer.addMessage("Created " + createdPageList.size() + " test pages.");
        return createdPageList;
    }
    
    /**
     * Creates config files needed to run JMeter test. 
     * @param config
     * @param messageBuffer
     * @param testAssets
     */
    private void createConfigFiles(TestConfiguration config, MessageBuffer messageBuffer, TestAssets testAssets)
    {
        logger.info("Creating config files.");
        messageBuffer.addMessage("Creating config files...");
        for(int i=1; i<= config.getNumUsers(); i++)
        {
            logger.debug("Creating config file: " + i + " of "  + config.getNumUsers());
            String filename = "config" + i + ".csv";
            if(!config.isCloneAsset())
            {
                ConfigFileUtil.createConfigFile(i, testAssets.getUsers()[i-1], config.getPageID(), config);
            }
            else
            {
                ConfigFileUtil.createConfigFile(i, testAssets.getUsers()[i-1], testAssets.getPages()[i-1],config);
            }
            testAssets.addConfigFile(filename);
            logger.debug("Created config file: " + filename);
        }
        messageBuffer.addMessage("Created " + config.getNumUsers() + " config files.");
    }

}

