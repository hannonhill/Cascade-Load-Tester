/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services;

import org.springframework.stereotype.Service;

/**
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
@Service
public class IdServiceImpl implements IdService
{
    private static long base = System.currentTimeMillis();

    /* (non-Javadoc)
     * @see com.hannonhill.loadtester.services.IdService#GetNewId()
     */
    @Override
    public long getNewId()
    {
        
        return base++;
    }

}
