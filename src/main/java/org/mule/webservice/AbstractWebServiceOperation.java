/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.webservice;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.webservice.api.WebServiceOperation;

public class AbstractWebServiceOperation implements WebServiceOperation
{

    private String name;
    private MessageProcessor handler;

    public AbstractWebServiceOperation(String name, MessageProcessor handler)
    {
        this.name = name;
        this.handler = handler;
    }

    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        return handler.process(event);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public MessageProcessor getHandler()
    {
        return handler;
    }

}
