package com.adobe.aem.my.wknd.site.core.services;

import java.util.List;

public interface OsgiFactoryConfig {
    public int getConfigId();
    public String getServiceName();
    public String getServiceUrl();
    public List<OsgiFactoryConfig> getAllOsgiFactoryConfigs();
}
