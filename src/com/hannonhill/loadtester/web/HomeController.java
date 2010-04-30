/*
 * Created on Mar 22, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannonhill.loadtester.domain.TestAssets;
import com.hannonhill.loadtester.domain.TestConfiguration;
import com.hannonhill.loadtester.services.IdService;
import com.hannonhill.loadtester.services.MessageBufferService;
import com.hannonhill.loadtester.services.test.SetupService;
import com.hannonhill.loadtester.services.test.TeardownService;
import com.hannonhill.loadtester.tasks.SerialTaskRunner;
import com.hannonhill.loadtester.utils.MessageBuffer;
import com.hannonhill.loadtester.utils.TaskPrepareUtil;

/**
 * Controller for running tests from the web.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
@Controller
@RequestMapping("/")
public class HomeController
{
    private Logger logger = Logger.getLogger(HomeController.class);
    @Autowired
    private MessageBufferService messageBufferService;
    @Autowired
    private IdService idService;    
    @Autowired
    private SetupService setupService;
    @Autowired
    private TeardownService teardownService;
    @Autowired
    private TaskExecutor executor;
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(Model model)
    {
        model.addAttribute("testConfiguration", new TestConfiguration());
        return "home/index";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String runTest(@ModelAttribute("testConfiguration") TestConfiguration config, Model model, HttpServletRequest request)
    {
        logger.info("Preparing enviornment before running tests");
        long id = idService.getNewId();
        request.getSession().setAttribute("id", id);
        TestAssets testAssets = new TestAssets();
        MessageBuffer messageBuffer = messageBufferService.newMessageBuffer(id);
        SerialTaskRunner taskRunner = TaskPrepareUtil.prepareTasks(messageBuffer, config, setupService, testAssets, executor, teardownService);
        taskRunner.execute();
        return "home/results";
    }
    
    @RequestMapping("/CheckMessages")
    public @ResponseBody String checkMessages(HttpServletRequest request)
    {
        StringBuilder sb = new StringBuilder();
        long id = (Long)request.getSession().getAttribute("id");
        logger.debug("Checking messages for id: " + id);
        MessageBuffer messageBuffer = messageBufferService.getMessageBuffer(id);
        if(messageBuffer == null)
        {
            logger.warn("Requesting invalid message buffer id: " + id);
            return "";
        }
        for(String message : messageBuffer.getMessages())
        {
            sb.append(message).append(" ");
        }
        return sb.toString();
    }
    
    @RequestMapping("/ws/{host}/{port}/{username}/{password}/{asset}/{group}/{numUsers}")
    public String runTest(Model model, @PathVariable("host") String host, @PathVariable("port") int port, 
            @PathVariable("username") String username, @PathVariable("password") String password, 
            @PathVariable("asset") String asset, @PathVariable("group") String group, @PathVariable("numUsers") int numUsers)
    {
        TestConfiguration config = new TestConfiguration();
        config.setGroups(group);
        config.setHost(host);
        config.setNumUsers(numUsers);
        config.setPageID(asset);
        config.setPassword(password);
        config.setPort(port);
        config.setUsername(username);
        
        SerialTaskRunner taskRunner = TaskPrepareUtil.prepareTasks(new MessageBuffer(), config, setupService, new TestAssets(), executor, teardownService);
        
        taskRunner.run();
        model.addAttribute("string", "Done!");
        return "common/ws";
    }
}
