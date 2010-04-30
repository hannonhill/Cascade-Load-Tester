/*
 * Created on Mar 29, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class PropertiesServiceImpl implements PropertiesService, ApplicationContextAware
{
    
    private ApplicationContext applicationContext;

    /* (non-Javadoc)
     * @see com.hannonhill.loadtester.services.PropertiesService#getProperties()
     */
    @Override
    public Properties getProperties()
    {
        return applicationContext.getBean("props", Properties.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;      
    }

}
