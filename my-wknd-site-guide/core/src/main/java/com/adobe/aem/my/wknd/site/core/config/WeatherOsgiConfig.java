package com.adobe.aem.my.wknd.site.core.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="WeatherOsgiConfig",description ="open weather api Config")
public @interface WeatherOsgiConfig {
    @AttributeDefinition(name = "location id",
    description = "Enter location id",
    type=AttributeType.LONG)
    long getLocationId();

    @AttributeDefinition(name = "api key",
    description = "open weather api key",
    type=AttributeType.STRING)
    String getOpenWeatherApiKey() default "Service Default";
    
}
