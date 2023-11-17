package com.adobe.aem.my.wknd.site.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
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
public class ExtendCoreCompTitleImpl implements Title{
    protected static final String RESOURCE_TYPE = "my-wknd-site/components/extendtitlecorecomp";

    @ValueMapValue(name=JcrConstants.JCR_TITLE)
    private String title;

    @ValueMapValue(name="extPropTitle")
    private String extPropTitle;

    @Override
    public String getText(){
        return "My WKND site Extended Title " + title;
    }
    public String getExtPropTitle(){
        return "Extended Propertry for Title comp " + extPropTitle;
    }

}
