package com.adobe.aem.my.wknd.site.core.models;

import java.util.List;

import com.adobe.aem.my.wknd.site.core.services.OsgiFactoryConfig;

public interface WkndOsgiConfig {
    public List<OsgiFactoryConfig> getAllOsgiConfigs();
}
