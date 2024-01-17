package com.adobe.aem.my.wknd.site.core.services.servicesImpl;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.my.wknd.site.core.config.WeatherOsgiConfig;
import com.adobe.aem.my.wknd.site.core.services.WeatherOsgi;
import com.adobe.aem.my.wknd.site.core.servlets.OpenWeatherMap;
@Component(service = WeatherOsgi.class,immediate = true)
@Designate(ocd = WeatherOsgiConfig.class)
public class WeatherOsgiImpl implements WeatherOsgi{
    private  long locationId = 0L;
    private  String apiKey = StringUtils.EMPTY;
    private WeatherOsgiConfig weatherOsgiConfig;
    private static Logger LOGGER = LoggerFactory.getLogger(WeatherOsgiImpl.class);
    
    @Activate
    protected void activate(WeatherOsgiConfig weatherOsgiConfig){
        this.weatherOsgiConfig = weatherOsgiConfig;
        this.locationId = weatherOsgiConfig.getLocationId();
        this.apiKey = weatherOsgiConfig.getOpenWeatherApiKey();
        LOGGER.info(weatherOsgiConfig.getLocationId()+"&"+weatherOsgiConfig.getOpenWeatherApiKey());
    }

    @Override
    public long getLocationId() {
        return this.locationId;
    }

    @Override
    public String getApiKey() {
       return this.apiKey;
    }
    
}
