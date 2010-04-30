/*
 * Created on Apr 16, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.utils;

import org.springframework.core.task.TaskExecutor;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.services.test.SetupService;
import com.hannonhill.loadtester.services.test.TeardownService;
import com.hannonhill.loadtester.tasks.PrepareResultsTask;
import com.hannonhill.loadtester.tasks.RunTestTask;
import com.hannonhill.loadtester.tasks.SerialTaskRunner;
import com.hannonhill.loadtester.tasks.SetupTask;
import com.hannonhill.loadtester.tasks.TeardownTask;

/**
 * Prepares tasks to run tests.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class TaskPrepareUtil
{
    /**
     * Adds tasks needed to run Jmeter tests to a SerialTaskRunner and returns it.
     * 
     * @param messageBuffer
     * @param config
     * @param setupService
     * @param testAssets
     * @param executor
     * @param teardownService
     * @return
     */
    public static SerialTaskRunner prepareTasks(MessageBuffer messageBuffer, TestConfiguration config, 
            SetupService setupService, TestAssets testAssets, TaskExecutor executor, TeardownService teardownService)
    {
        SetupTask setupTask = new SetupTask(messageBuffer, config, setupService, testAssets);
        SerialTaskRunner taskExecutor = new SerialTaskRunner(executor);
        taskExecutor.addTask(setupTask);
        RunTestTask runTestTask = new RunTestTask(messageBuffer, config);
        taskExecutor.addTask(runTestTask);
        TeardownTask teardownTask = new TeardownTask(config, testAssets, teardownService, messageBuffer);
        taskExecutor.addTask(teardownTask);
        PrepareResultsTask prepareTask = new PrepareResultsTask(messageBuffer);
        taskExecutor.addTask(prepareTask);
        return taskExecutor;
    }
}
