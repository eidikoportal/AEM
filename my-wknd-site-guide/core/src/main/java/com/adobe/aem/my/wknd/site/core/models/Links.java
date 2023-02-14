package com.adobe.aem.my.wknd.site.core.models;

import com.adobe.aem.my.wknd.site.core.services.ExternalLinkBuilderService;
import com.day.cq.commons.Externalizer;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Links {
    //protected static final String RESOURCE_TYPE = "wknd1/components/content/linkcomponent";
    Logger log = LoggerFactory.getLogger(this.getClass());
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
    @OSGiService
    private ExternalLinkBuilderService externalLinkBuilderService;

    @Reference
    private Externalizer externalizer;
    public String getLink() {
        return link;
    }
    public String getInternal() {
        log.info("getInternal==");
        String path= externalLinkBuilderService.getExternalLink(resourceResolver,internal);
        log.info("path==");
        log.info(path);
        return path;
    }
    public String getExternal() {
        // TODO Auto-generated method stub
        return external;
    }
    public String getInternalLabel() {
        return internalLabel;
    }
    public String getExternalLabel() {
        return externalLabel;
    }
}
