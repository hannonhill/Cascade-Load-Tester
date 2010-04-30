/*
 * Created on Mar 22, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.webservices;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.hannonhill.www.ws.ns.AssetOperationService.Asset;
import com.hannonhill.www.ws.ns.AssetOperationService.AssetOperationHandler;
import com.hannonhill.www.ws.ns.AssetOperationService.AssetOperationHandlerServiceLocator;
import com.hannonhill.www.ws.ns.AssetOperationService.Authentication;
import com.hannonhill.www.ws.ns.AssetOperationService.CreateResult;
import com.hannonhill.www.ws.ns.AssetOperationService.Identifier;
import com.hannonhill.www.ws.ns.AssetOperationService.OperationResult;

/**
 * Base class for Util classes that creates and deletes assets on a cascade instance
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public abstract class AssetUtil
{

    protected Logger logger = Logger.getLogger(this.getClass());
    protected AssetOperationHandler handler;
    protected Authentication authentication;

    /**
     * 
     * @param hostname
     * @param port
     * @param authentication
     */
    public AssetUtil(String hostname, int port, Authentication authentication)
    {
        this.authentication = authentication;
        AssetOperationHandlerServiceLocator locator = new AssetOperationHandlerServiceLocator();
        try
        {
            logger.debug("Locating service endpoint");
            //the name of the port (ie "AssetOperationService" for Cascade Webservices) and the url to the service"
            locator.setEndpointAddress("AssetOperationService", "http://"+hostname+":"+String.valueOf(port)+"/ws/services/AssetOperationService");
            handler = locator.getAssetOperationService();
        }
        catch (ServiceException e)
        {
            logger.error("Error creating an AssetCreationUtil: ", e);
        }
    }
    
    /**
     * Creates an asset on the cascade server instance 
     * @param asset
     * @return
     * @throws RemoteException
     */
    protected String create(Asset asset) throws RemoteException
    {
        CreateResult result = handler.create(authentication, asset);
        if(result == null)
        {
            logger.error("Unknown error occured when creating asset on Cascade server instance!");
            return null;
        }
        logger.debug("Created asset on Cascade server instance: " + result.getMessage());
        return result.getCreatedAssetId();
        
    }
    
    /**
     * Deletes an asset with the given id from the cascade server instance
     * @param id
     * @return
     * @throws RemoteException
     */
    protected boolean delete(Identifier id) throws RemoteException
    {
        OperationResult result = handler.delete(authentication, id);
        if(result == null)
        {
            logger.error("Unknown error occured when deleting asset on Cascade server instance!");
            return false;
        }
        logger.debug("Deleted asset from Cascade Server instance: " + result.getMessage());
        return result.getSuccess() == "success";
    }

}