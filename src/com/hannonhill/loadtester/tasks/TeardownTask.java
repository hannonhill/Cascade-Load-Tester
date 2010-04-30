/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.tasks;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.services.test.TeardownService;
import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Task that runs the teardown service.
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class TeardownTask implements Runnable
{
    private TestConfiguration testConfig;
    private TestAssets testAssets;
    private TeardownService teardownService;
    private MessageBuffer messageBuffer;
    
    /**
     * Creates a new TeardownTask
     * @param testConfig
     * @param testAssets
     * @param teardownService
     * @param messageBuffer
     */
    public TeardownTask(TestConfiguration testConfig, TestAssets testAssets, TeardownService teardownService, MessageBuffer messageBuffer)
    {
        this.testConfig = testConfig;
        this.testAssets = testAssets;
        this.teardownService = teardownService;
        this.messageBuffer = messageBuffer;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        teardownService.teardownAfterTest(testConfig, messageBuffer, testAssets);
    }

}
