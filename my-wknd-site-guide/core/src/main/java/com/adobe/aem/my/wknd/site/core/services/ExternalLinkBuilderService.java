package com.adobe.aem.my.wknd.site.core.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface ExternalLinkBuilderService{
    public String getExternalLink(final ResourceResolver resourceResolver,final String internalPath);
}
