/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.tasks;

import org.apache.jmeter.JMeter;

import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Task for running the actual jmeter test.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class RunTestTask implements Runnable
{

    private MessageBuffer messageBuffer;
    private ThreadGroup rootThreadGroup;
    private TestConfiguration config;
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        messageBuffer.addMessage("Begining to run JMeter test.");
        JMeter jmeter = new JMeter();
        String dir = System.getProperty("catalina.home") + "/webapps/LoadTester/";
        jmeter.start(new String[] {"-n", "-t", dir + "TestPlan.jmx", "-p", dir + "jmeter.properties", "-d", dir, "-l", dir + "results.xml", "-j", 
                dir + "jmeter.log", "-Jsearch_paths=" + dir + "WEB-INF/lib", "-Jgroup.threads="+config.getNumUsers()});
        messageBuffer.addMessage("JMeter test started");
        messageBuffer.addMessage("Tests running... this may take a while");
        //poll the jmeter thread group until all of the threads have finished        
        boolean running = true;
        while(running)
        {
            wait(5);
            ThreadGroup jmeterThreadGroup = getThreadGroup("JMeterThreadGroup");
            messageBuffer.noBreakAddMessage(".");
            if(jmeterThreadGroup.activeCount() == 0)
            {
                running = false;
                //destroy the empty thread group because JMeter does not.
                jmeterThreadGroup.destroy();
            }
        }
        //add break before tests finished message
        messageBuffer.addMessage("");
        messageBuffer.addMessage("JMeter tests finished.");
    }
    
    /**
     * Create a new RunTestTask which uses the given message buffer.
     * 
     * @param messageBuffer
     */
    public RunTestTask(MessageBuffer messageBuffer, TestConfiguration config)
    {
        this.messageBuffer = messageBuffer;
        this.config = config;
    }
    
    private ThreadGroup getRootThreadGroup() 
    {
        if (rootThreadGroup != null)
        {
            return rootThreadGroup;
        }
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        ThreadGroup ptg;
        while ((ptg = tg.getParent()) != null)
            tg = ptg;
        return tg;
    }
    
    private ThreadGroup[] getAllThreadGroups()
    {
        final ThreadGroup root = getRootThreadGroup();
        int nAlloc = root.activeGroupCount();
        int n = 0;
        ThreadGroup[] groups;
        do 
        {
            nAlloc *= 2;
            groups = new ThreadGroup[ nAlloc ];
            n = root.enumerate(groups, true);
        } while (n == nAlloc);
     
        ThreadGroup[] allGroups = new ThreadGroup[n+1];
        allGroups[0] = root;
        System.arraycopy(groups, 0, allGroups, 1, n);
        return allGroups;
    }
    
    private ThreadGroup getThreadGroup(final String name) 
    {
        if (name == null)
            throw new NullPointerException("Null name");
        final ThreadGroup[] groups = getAllThreadGroups();
        for (ThreadGroup group : groups)
            if (group.getName().equals(name))
                return group;
        return null;
    }
    
    private void wait(int n)
    {
        long t0,t1;
        t0=System.currentTimeMillis();
        do
        {
            t1=System.currentTimeMillis();
        }
        while (t1-t0<(1000*n));
}

}
