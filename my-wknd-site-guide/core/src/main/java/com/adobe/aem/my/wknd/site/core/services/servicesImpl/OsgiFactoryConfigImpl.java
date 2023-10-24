package com.adobe.aem.my.wknd.site.core.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.my.wknd.site.core.config.WnkdOsgiFactoryConfig;
import com.adobe.aem.my.wknd.site.core.services.OsgiFactoryConfig;

@Component(service = OsgiFactoryConfig.class,configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = WnkdOsgiFactoryConfig.class,factory = true)
public class OsgiFactoryConfigImpl implements OsgiFactoryConfig{
    private static final Logger logger = LoggerFactory.getLogger(OsgiFactoryConfigImpl.class);
    private int configId;
    private String serviceName;
    private String seriveUrl;
    private List<OsgiFactoryConfig> configList;

    @Activate
    @Modified
    protected void activate(final WnkdOsgiFactoryConfig wnkdOsgiConfig){
        this.configId = wnkdOsgiConfig.configId();
         this.serviceName = wnkdOsgiConfig.serviceName();
          this.seriveUrl = wnkdOsgiConfig.serviceUrl();
           
    }
    @Reference(service = OsgiFactoryConfig.class,cardinality = ReferenceCardinality.MULTIPLE,policy = ReferencePolicy.DYNAMIC)
    public void bindOsgiFactoryConfig(final OsgiFactoryConfig osgiFactoryConfig ){
            if(configList == null){
                configList =new ArrayList<>();
            }
            configList.add(osgiFactoryConfig);
    }
    public void unbindOsgiFactoryConfig(final OsgiFactoryConfig osgiFactoryConfig ){
            
            configList.remove(osgiFactoryConfig);
    }
    @Override
    public int getConfigId() {
        return configId;
    }

    @Override
    public String getServiceName() {
       return serviceName;
    }

    @Override
    public String getServiceUrl() {
        return seriveUrl;
    }

    @Override
    public List<OsgiFactoryConfig> getAllOsgiFactoryConfigs() {
        return configList;
    }
    
}
