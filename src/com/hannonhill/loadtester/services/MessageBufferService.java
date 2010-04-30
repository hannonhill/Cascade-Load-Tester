/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services;

import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Stores and retrieves message buffers.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public interface MessageBufferService
{
    /**
     * Creates a new {@link MessageBuffer}, stores it and returns the new MessageBuffer.
     * 
     * @param id
     * @return new message buffer
     */
    public MessageBuffer newMessageBuffer(long id);
    
    /**
     * Retrieves a {@link MessageBuffer}.
     * @param id
     * @return retrieved message buffer
     */
    public MessageBuffer getMessageBuffer(long id);
}
