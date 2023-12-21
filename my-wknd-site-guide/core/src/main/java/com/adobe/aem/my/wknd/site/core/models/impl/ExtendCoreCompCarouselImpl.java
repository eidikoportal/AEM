package com.adobe.aem.my.wknd.site.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;

import com.adobe.cq.wcm.core.components.models.Carousel;
import com.adobe.cq.wcm.core.components.models.Title;
import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Title.class},
        resourceType = {ExtendCoreCompTitleImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ExtendCoreCompCarouselImpl implements Carousel{
    protected static final String RESOURCE_TYPE = "my-wknd-site/components/extendcaroucelcorecomp";

    @ValueMapValue(name=JcrConstants.JCR_TITLE)
    private String title;

    @ValueMapValue(name="extPropTitle")
    private String extPropTitle;

    @Override
    public boolean isControlsPrepended(){
        return false;
    }
    public String getExtPropTitle(){
        return "Extended Propertry for carousel comp " + extPropTitle;
    }

}
