package com.adobe.aem.my.wknd.site.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Section {
   // protected static final String RESOURCE_TYPE = "wknd1/components/content/linkcomponent";
   Logger log = LoggerFactory.getLogger(this.getClass());
    @ValueMapValue
    private String section;

    @ChildResource
    private List<Links> linksmultifield;

    public String getSection() {
        return section;
    }

    public List<Links> getLinksmultifield() {
        return linksmultifield;
    }

    public boolean isValidLink() {
        log.info("isValidLink==");
        log.info("=="+linksmultifield.size());
        return linksmultifield.size()>0?true:false;
    }
   
    
   
    
}
