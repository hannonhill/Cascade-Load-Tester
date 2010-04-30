/*
 * Created on Mar 29, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services.test;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public interface SetupService
{
    
    /**
     * Generates necessary assets on the Cascade Server instance and any Server Side configuration files that are needed.
     * 
     * @param config
     */
    public void setupForTest(TestConfiguration config, MessageBuffer messageBuffer, TestAssets testAssets);

}
