/*
 * Created on Mar 22, 2010 by Aubrey
 * 
 * Copyright(c) 2000-2010 Hannon Hill Corporation.  All rights reserved.
 */
package com.hannonhill.loadtester.webservices;

import java.rmi.RemoteException;

import com.hannonhill.www.ws.ns.AssetOperationService.Asset;
import com.hannonhill.www.ws.ns.AssetOperationService.Authentication;
import com.hannonhill.www.ws.ns.AssetOperationService.EntityTypeString;
import com.hannonhill.www.ws.ns.AssetOperationService.Identifier;
import com.hannonhill.www.ws.ns.AssetOperationService.User;
import com.hannonhill.www.ws.ns.AssetOperationService.UserAuthTypes;

/**
 * 
 * @author  Aubrey Rhodes
 * @version $Id: $
 * @since   6.7
 */
public class UserUtil extends AssetUtil
{

    /**
     * Utility class for working with users on a Cascade Server instance.
     * 
     * @param hostname
     * @param port
     * @param authentication
     */
    public UserUtil(String hostname, int port, Authentication authentication)
    {
        super(hostname, port, authentication);
    }

    /**
     * Creates a new {@link User} on the remote Cascade instance with the
     * given username and a password the same as the username.
     * @param username
     * @param groups a comma seperated list of groups to add the user to
     * @return id of created user or null if user was not created
     */
    public String createUser(String username, String groups)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(username);
        user.setFullName(username);
        user.setEmail(username + "@hannonhill.com");
        user.setAuthType(UserAuthTypes.normal);
        user.setGroups(groups);
        user.setRole("Administrator");
        user.setEnabled(true);
        Asset asset = new Asset();
        asset.setUser(user);
        logger.debug("Creating new User on remote Cascade instance");
        try
        {
            return create(asset);
        }
        catch (RemoteException e)
        {
            logger.error("Error creating User on remote Cascade instance: ", e);
            return null;
        }
    }

    /**
     * Deletes a user with the given username from the Cascade instance.
     * 
     * @param id
     */
    public void deleteUser(String id)
    {
        if (id == null)
        {
            logger.debug("Skipping deleting user with null username");
            return;
        }
        logger.debug("Deleting a user from the cascade server instace: " + id);
        Identifier identifier = new Identifier();
        identifier.setId(id);
        identifier.setType(EntityTypeString.user);

        try
        {
            delete(identifier);
        }
        catch (RemoteException e)
        {
            logger.error("Error deleting user on remote Cascade instance: ", e);
        }
    }

}
