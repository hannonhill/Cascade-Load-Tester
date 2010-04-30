/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Implementation of MessageBufferService
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
@Service
public class MessageBufferImpl implements MessageBufferService
{
    private Map<Long, MessageBuffer> map = new HashMap<Long, MessageBuffer>();

    /* (non-Javadoc)
     * @see com.hannonhill.loadtester.services.MessageBufferService#GetMessageBuffer(long)
     */
    @Override
    public MessageBuffer getMessageBuffer(long id)
    {        
        return map.get(id);
    }

    /* (non-Javadoc)
     * @see com.hannonhill.loadtester.services.MessageBufferService#NewMessageBuffer(long)
     */
    @Override
    public MessageBuffer newMessageBuffer(long id)
    {
        MessageBuffer newMessageBuffer = new MessageBuffer();
        map.put(id, newMessageBuffer);
        return newMessageBuffer;
    }   
}
