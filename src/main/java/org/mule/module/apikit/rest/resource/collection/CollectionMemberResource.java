
package org.mule.module.apikit.rest.resource.collection;

import org.mule.module.apikit.rest.RestException;
import org.mule.module.apikit.rest.RestRequest;
import org.mule.module.apikit.rest.operation.RestOperationType;
import org.mule.module.apikit.rest.resource.AbstractHierarchicalRestResource;

import java.util.EnumSet;
import java.util.Set;

public class CollectionMemberResource extends AbstractHierarchicalRestResource
{

    protected CollectionResource collectionResource;

    public CollectionMemberResource(String name)
    {
        super(name);
    }

    @Override
    protected Set<RestOperationType> getSupportedActionTypes()
    {
        return EnumSet.of(RestOperationType.RETRIEVE, RestOperationType.EXISTS, RestOperationType.UPDATE,
            RestOperationType.DELETE);
    }

    @Override
    public void handle(RestRequest restRequest) throws RestException
    {
        restRequest.getMuleEvent().setFlowVariable(getName() + "Id", restRequest.getNextPathElement());
        super.handle(restRequest);
    }

    public void setCollectionResource(CollectionResource collectionResource)
    {
        this.collectionResource = collectionResource;
    }

    public CollectionResource getCollectionResource()
    {
        return collectionResource;
    }

}