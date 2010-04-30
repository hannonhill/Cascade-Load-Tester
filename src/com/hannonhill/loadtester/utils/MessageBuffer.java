/*
 * Created on Mar 29, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores messages from a task so that they can be displayed asynchronously
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class MessageBuffer
{
    private List<String> messages = new ArrayList<String>();
    
    /**
     * Adds a message with a break to the buffer.
     * @param message
     */
    public synchronized void addMessage(String message)
    {
        messages.add(message + "<br />");
    }
    
    
    /**
     * Adds a message to the buffer with no break.
     * @param message
     */
    public synchronized void noBreakAddMessage(String message)
    {
        messages.add(message);
    }
    
    /**
     * Returns an array of all the messages in the buffer and clears the buffer
     * 
     * @return
     */
    public synchronized String[] getMessages()
    {
        String[] result = messages.toArray(new String[0]);
        messages.clear();
        return result;
    }
}
