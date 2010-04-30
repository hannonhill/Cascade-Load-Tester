/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services.test;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Service for removing assets from Cascade Server instance after tests have run.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public interface TeardownService
{
    /**
     * Removes pages and users created on cascade server instance.
     * 
     * @param config
     * @param messageBuffer
     * @param testAssets
     */
    public void teardownAfterTest(TestConfiguration config, MessageBuffer messageBuffer, TestAssets testAssets);
}
