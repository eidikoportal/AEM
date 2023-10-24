package com.adobe.aem.my.wknd.site.core.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Wnkd OsgiFactoryConfig",description ="Wnkd Osgi Factory Config")
public @interface WnkdOsgiFactoryConfig {
    @AttributeDefinition(name = "Wnkd config id",
    description = "Enter Wnkd config id",
    type=AttributeType.INTEGER)
    int configId();

    @AttributeDefinition(name = "Wnkd service name",
    description = "Enter Wnkd service name",
    type=AttributeType.STRING)
    String serviceName() default "Service Default";
    
    @AttributeDefinition(name = "Wnkd service url",
    description = "Enter Wnkd service url",
    type=AttributeType.STRING)
    String serviceUrl() default "Service Url#";
}
