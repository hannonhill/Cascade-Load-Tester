/*
 * Created on Apr 2, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.tasks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.task.TaskExecutor;

/**
 * Holds a list of tasks and runs them in order.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class SerialTaskRunner implements Runnable
{
    private TaskExecutor executor;
    private List<Runnable> tasks = new ArrayList<Runnable>();

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        for(Runnable task : tasks)
        {
            // if the this object is in the list of tasks to run, don't run it!
            if(task!=this)
                task.run();
        }
    }
    
    /**
     * Add a task to the list to be run.
     * @param task
     */
    public void addTask(Runnable task)
    {
        tasks.add(task);
    }
    
    /**
     * Begin running the tasks in the list.
     */
    public void execute()
    {
        executor.execute(this);
    }
    
    /**
     * Creates a new SerialTaskRunner with the given TaskExecutor
     * @param executor
     */
    public SerialTaskRunner(TaskExecutor executor)
    {
        this.executor = executor;
    }

}
