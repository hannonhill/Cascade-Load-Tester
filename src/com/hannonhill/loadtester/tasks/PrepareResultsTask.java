/*
 * Created on Apr 12, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.hannonhill.loadtester.utils.MessageBuffer;

/**
 * Task to transform XML results from JMeter tests into html.
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class PrepareResultsTask implements Runnable
{
    private MessageBuffer messageBuffer;

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        messageBuffer.addMessage("Preparing results from JMeter Tests...");
        String dir = System.getProperty("catalina.home") + "/webapps/LoadTester/";        
        Source xsl = new StreamSource(new File(dir + "/WEB-INF/jmeter-results-detail-report_21.xsl"));        
        try
        {
            Source xmlInput = new StreamSource(new FileInputStream(dir + "results.xml"));
            Result out = new StreamResult(new FileOutputStream(dir + "results.html"));
        
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xsl);
            transformer.transform(xmlInput, out);
            messageBuffer.addMessage("<a href=\"results.html\" target=\"results\">View Results</a>");
        }
        catch (TransformerConfigurationException e)
        {
            messageBuffer.addMessage("Error preparing results (see logs for more details): " + e.getMessage());
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e)
        {
            messageBuffer.addMessage("Error preparing results (see logs for more details): " + e.getMessage());
            e.printStackTrace();
        }
        catch (TransformerException e)
        {
            messageBuffer.addMessage("Error preparing results (see logs for more details): " + e.getMessage());
            e.printStackTrace();
        }
        catch (FileNotFoundException e1)
        {
            messageBuffer.addMessage("Error preparing results (see logs for more details): " + e1.getMessage());
            e1.printStackTrace();
        }

    }
    
    public PrepareResultsTask(MessageBuffer messageBuffer)
    {
        this.messageBuffer = messageBuffer;
    }

}
