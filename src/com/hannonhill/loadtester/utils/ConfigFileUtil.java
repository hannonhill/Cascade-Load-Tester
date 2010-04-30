/*
 * Created on Apr 5, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.hannonhill.loadtester.domain.TestConfiguration;

/**
 * This class creates and destroys the config files used to run JMeter Tests.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class ConfigFileUtil
{
    private static final String DIR = System.getProperty("catalina.home") + "/webapps/LoadTester/";
    
    private static Logger logger = Logger.getLogger(ConfigFileUtil.class);
    
    /**
     * Creates a new file with the given file name and content.
     * 
     * @param fileName
     * @param content
     */
    private static void createFile(String fileName, String content)
    {
        File f = new File(DIR, fileName);
        if(f.exists())
        {
            f.delete();
        }
        try
        {
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(content);
            writer.close();
        }
        catch (IOException e)
        {
            logger.error("Error creating a file: ", e);
        }        
    }
    
    /**
     * Creates a config file for a given JMeter user.
     * 
     * @param userNum
     * @param username
     * @param assetId
     * @param config TestConfiguration object which holds host name and port number
     */
    public static void createConfigFile(int userNum, String username, String assetId, TestConfiguration config)
    {
        createFile("config" + userNum + ".csv", username + "," + username + "," + assetId + "," + config.getHost() + "," + config.getPort());
    }
}
