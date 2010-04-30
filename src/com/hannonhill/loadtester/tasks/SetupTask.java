/*
 * Created on Mar 29, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.tasks;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.services.test.SetupService;
import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Task that sets up test environment prior to running tests.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class SetupTask implements Runnable
{
    private MessageBuffer messageBuffer;
    private TestConfiguration config;
    private SetupService setupService;
    private TestAssets testAssets;
    
    /** 
     * @param messageBuffer
     * @param config
     */
    public SetupTask(MessageBuffer messageBuffer, TestConfiguration config, SetupService setupService, TestAssets testAssets)
    {
        this.messageBuffer = messageBuffer;
        this.config = config;
        this.setupService = setupService;
        this.testAssets = testAssets;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        setupService.setupForTest(config, messageBuffer, testAssets);        
    }

}
