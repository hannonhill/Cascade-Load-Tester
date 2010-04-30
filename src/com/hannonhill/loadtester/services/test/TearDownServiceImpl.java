/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services.test;

import org.springframework.stereotype.Service;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.utils.MessageBuffer;
import com.hannonhill.loadtester.webservices.PageUtil;
import com.hannonhill.loadtester.webservices.UserUtil;
import com.hannonhill.www.ws.ns.AssetOperationService.Authentication;

/**
 * Service for deleting test assets.
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
@Service
public class TearDownServiceImpl implements TeardownService
{
    /* (non-Javadoc)
     * @see com.hannonhill.loadtester.services.test.TeardownService#teardownAfterTest(com.hannonhill.loadtester.domain.TestConfiguration, 
     * com.hannonhill.loadtester.utils.MessageBuffer, com.hannonhill.loadtester.domain.TestAssets)
     */
    @Override
    public void teardownAfterTest(TestConfiguration config, MessageBuffer messageBuffer, TestAssets testAssets)
    {
        messageBuffer.addMessage("Tearing down test assets.");
        Authentication auth = new Authentication();
        auth.setPassword(config.getPassword());
        auth.setUsername(config.getUsername());
        PageUtil pageUtil = new PageUtil(config.getHost(), config.getPort(), auth);
        messageBuffer.addMessage("Removing test pages...");
        for(String pageId : testAssets.getPages())
        {
            pageUtil.deletePage(pageId);
            testAssets.removePage(pageId);
        }
        messageBuffer.addMessage("Test pages removed.");
        UserUtil userUtil = new UserUtil(config.getHost(), config.getPort(), auth);
        messageBuffer.addMessage("Removing test users...");
        for(String userId : testAssets.getUsers())
        {
            userUtil.deleteUser(userId);
            testAssets.removeUser(userId);
        }
        messageBuffer.addMessage("Test users removed.");
        messageBuffer.addMessage("Teardown complete.");
    }
    
}
