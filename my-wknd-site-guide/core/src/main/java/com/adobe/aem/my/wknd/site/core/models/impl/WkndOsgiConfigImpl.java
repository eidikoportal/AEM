package com.adobe.aem.my.wknd.site.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;

import com.adobe.aem.my.wknd.site.core.models.WkndOsgiConfig;
import com.adobe.aem.my.wknd.site.core.services.OsgiFactoryConfig;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.scripting.jsp.taglib.SlingFunctions;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = WkndOsgiConfig.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WkndOsgiConfigImpl implements WkndOsgiConfig{

    @OSGiService
    OsgiFactoryConfig osgiFactoryConfig;
    
    @Override
    public List<OsgiFactoryConfig> getAllOsgiConfigs() {
       return osgiFactoryConfig.getAllOsgiFactoryConfigs();
    }
    
}
