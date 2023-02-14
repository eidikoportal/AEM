package com.adobe.aem.my.wknd.site.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class LinkSections {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @ChildResource
    private List<Section> sections;

    public List<Section> getSections() {
        return sections;
    }

    public boolean isValidSection() {
        log.info("isvalidsection==");
        log.info("=="+sections.size());
        return sections.size()>0?true:false;
    }

    
}
