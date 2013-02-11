/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.wsapi.ws;

import org.mule.module.wsapi.AbstractWebServiceInterface;

public class WSWebServiceInterface extends AbstractWebServiceInterface
{

    protected WSOperationResolutionMode operationResolutionMode = WSOperationResolutionMode.SOAP_ACTION;

    public WSWebServiceInterface(String name)
    {
        super(name);
    }

    public WSOperationResolutionMode getOperationResolutionMode()
    {
        return operationResolutionMode;
    }

    public void setOperationResolutionMode(WSOperationResolutionMode operationResolutionMode)
    {
        this.operationResolutionMode = operationResolutionMode;
    }
}
