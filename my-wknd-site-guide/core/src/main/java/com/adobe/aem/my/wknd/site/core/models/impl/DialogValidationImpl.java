package com.adobe.aem.my.wknd.site.core.models.impl;




import javax.annotation.Resource;

import com.adobe.aem.my.wknd.site.core.models.DialogValidation;
import com.adobe.aem.my.wknd.site.core.services.ExternalLinkBuilderService;
import com.day.cq.commons.Externalizer;


import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
@Model(
        adaptables = {SlingHttpServletRequest.class,Resource.class},
        adapters = {DialogValidation.class},
        resourceType = {DialogValidationImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class DialogValidationImpl implements DialogValidation{
    Logger log = LoggerFactory.getLogger(this.getClass());

    protected static final String RESOURCE_TYPE = "wknd1/components/content/dialogvalidation";
    @Self
    private SlingHttpServletRequest request;
    
    @OSGiService
    private ModelFactory modelFactory;
    
    @ValueMapValue
    private String title;

    @ValueMapValue
    private String textarea;

    @ValueMapValue
    private String link;
    @ValueMapValue
    private String internal;
    @ValueMapValue
    private String internalLabel;
    @ValueMapValue
    private String external;
    @ValueMapValue
    private String externalLabel;
    @SlingObject
    ResourceResolver resourceResolver;
    @Reference
    private ResourceResolverFactory resolverFactory;
    
    @OSGiService
    private ExternalLinkBuilderService externalLinkBuilderService;

    @Reference
    private Externalizer externalizer;
   
    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return title;
    }

    @Override
    public String getTextArea() {
        // TODO Auto-generated method stub
        return textarea;
    }

    @Override
    public String getLink() {
        // TODO Auto-generated method stub
        return link;
    }

    @Override
    public boolean isEmpty() {
        if (StringUtils.isBlank(title)) {
            // title is missing, but required
            return true;
        } else if (StringUtils.isBlank(textarea)) {
            return true;
        }
        else if (StringUtils.isBlank(link)) {
            // A link is required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }

    @Override
    public String getInternal() {
        log.info("getInternal==");
        String path= externalLinkBuilderService.getExternalLink(resourceResolver,internal);
        log.info("path==");
        log.info(path);
        return path;
    }

    @Override
    public String getExternal() {
        // TODO Auto-generated method stub
        return external;
    }

    @Override
    public String getInternalLabel() {
        return internalLabel;
    }

    @Override
    public String getExternalLabel() {
        return externalLabel;
    }

    
}
