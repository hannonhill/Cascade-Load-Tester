/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services;

/**
 * Generates unique job ids.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public interface IdService
{
    
    /**
     * Generates a new id and returns it.
     * @return
     */
    public long getNewId();
}
