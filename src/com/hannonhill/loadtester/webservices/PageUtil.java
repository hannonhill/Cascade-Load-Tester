/*
 * Created on Mar 22, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.webservices;

import java.rmi.RemoteException;

import com.hannonhill.www.ws.ns.AssetOperationService.Asset;
import com.hannonhill.www.ws.ns.AssetOperationService.Authentication;
import com.hannonhill.www.ws.ns.AssetOperationService.CreateResult;
import com.hannonhill.www.ws.ns.AssetOperationService.EntityTypeString;
import com.hannonhill.www.ws.ns.AssetOperationService.Identifier;
import com.hannonhill.www.ws.ns.AssetOperationService.Page;

/**
 * Service for creating pages in a remote Cascade Server Instance
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class PageUtil extends AssetUtil
{
    /**
     * 
     * @param hostname
     * @param port
     * @param authentication
     */
    public PageUtil(String hostname, int port, Authentication authentication)
    {
        super(hostname, port, authentication);
    }

    /**
     * Creates a new page on Cascade instance based on a page existing in the instance.
     * @param id ID of page to copy
     * @return id of new page, null if page was not created.
     */
    public String createPage(String id, String pageName)
    {
        Identifier identifier = new Identifier();
        identifier.setId(id);
        identifier.setType(EntityTypeString.page);
        Page page;
        try
        {
            logger.debug("Reading page from Cascade Server instence to copy: " + id);
            page = handler.read(authentication, identifier).getAsset().getPage();
        }
        catch (RemoteException e1)
        {
            logger.error("Error reading page from Cascade Server instance: ", e1);
            return null;
        }
        //clear out the id since we're creating a new page
        page.setId(null);
        //so that each page has a unique name
        page.setName(pageName + System.currentTimeMillis());
        Asset asset = new Asset();
        asset.setPage(page);
        try
        {
            logger.debug("Creating new page on Cascade Server Instence");
            CreateResult result = handler.create(authentication, asset);
            if (result.getMessage() != null)
                logger.info("Result from creating page: " + result.getMessage());
            return result.getCreatedAssetId();
        }
        catch (RemoteException e)
        {
            logger.error("Error creating a page on Cascade Server instance: ", e);
            return null;
        }
    }

    public void deletePage(String id)
    {
        logger.debug("Deleting a page from Cascade Server instance: " + id);
        Identifier identifier = new Identifier();
        identifier.setId(id);
        identifier.setType(EntityTypeString.page);
        try
        {
            delete(identifier);
        }
        catch (RemoteException e)
        {
            logger.error("Error deleting a page on Cascade Server instance: ", e);
        }
    }
}
