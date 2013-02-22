
package org.mule.module.apikit.rest.operation;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.expression.ExpressionManager;
import org.mule.module.apikit.AbstractWebServiceOperation;
import org.mule.module.apikit.UnauthorizedException;
import org.mule.module.apikit.rest.MediaTypeNotAcceptableException;
import org.mule.module.apikit.rest.RestException;
import org.mule.module.apikit.rest.RestRequest;
import org.mule.module.apikit.rest.UnsupportedMediaTypeException;
import org.mule.module.apikit.rest.representation.RepresentationFactory;
import org.mule.module.apikit.rest.representation.RepresentationType;
import org.mule.module.apikit.rest.util.RestContentTypeParser;

import com.google.common.net.MediaType;

import java.util.Collection;
import java.util.HashSet;

public abstract class AbstractRestOperation extends AbstractWebServiceOperation implements RestOperation
{

    protected RestOperationType type;
    protected Collection<RepresentationType> representations = new HashSet<RepresentationType>();

    @Override
    public RestOperationType getType()
    {
        return type;
    }

    public void setRepresentations(Collection<RepresentationType> representations)
    {
        this.representations = representations;
    }

    @Override
    public MuleEvent handle(RestRequest request) throws RestException
    {
        ExpressionManager expManager = request.getMuleEvent().getMuleContext().getExpressionManager();

        if (accessExpression != null && !expManager.evaluateBoolean(accessExpression, request.getMuleEvent()))
        {
            throw new UnauthorizedException(this);
        }
        if (!getRepresentations().isEmpty())
        {
            validateSupportedRequestMediaType(request);
            validateAcceptableResponeMediaType(request);
        }
        try
        {
            return getHandler().process(request.getMuleEvent());
        }
        catch (MuleException e)
        {
            throw new RestException();
        }
    }

    protected void validateSupportedRequestMediaType(RestRequest request)
        throws UnsupportedMediaTypeException
    {
        boolean valid = false;
        for (RepresentationType representation : representations)
        {
            if (representation.getMediaType().is(request.getProtocolAdaptor().getRequestMediaType()))
            {
                valid = true;
                break;
            }
        }
        if (!valid)
        {
            throw new UnsupportedMediaTypeException();
        }
    }

    protected void validateAcceptableResponeMediaType(RestRequest request)
        throws MediaTypeNotAcceptableException
    {
        MediaType bestMatch = RestContentTypeParser.bestMatch(representations, request.getProtocolAdaptor()
            .getAcceptableResponseMediaTypes());
        if (bestMatch == null)
        {
            throw new MediaTypeNotAcceptableException();
        }
    }

    @Override
    public Collection<RepresentationType> getRepresentations()
    {
        return representations;
    }
    
    @Override
    public RepresentationFactory getResponseRepresentationFactory()
    {
        // TODO Auto-generated method stub
        return null;
    }
}