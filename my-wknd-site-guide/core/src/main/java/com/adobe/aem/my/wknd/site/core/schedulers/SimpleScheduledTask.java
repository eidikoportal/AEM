/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.my.wknd.site.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * A simple demo for cron-job like tasks that get executed regularly.
 * It also demonstrates how property values can be set. Users can
 * set the property values in /system/console/configMgr
 */
@Designate(ocd=SchedulerConfig.class)
@Component(service=Runnable.class,immediate = true)
public class SimpleScheduledTask implements Runnable {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String myParameter;
    
    @Reference 
    Scheduler scheduler;
  
    private int schedulerId;
    
    @Override
    public void run() {
        logger.debug("SimpleScheduledTask is now running, myParameter='{}'", myParameter);
    }

    @Activate
    protected void activate(final SchedulerConfig config) {
        myParameter = config.myParameter();
        schedulerId = config.scheduler_name().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(){
        removeScheduler();
    }
    private void addScheduler(SchedulerConfig config){
            ScheduleOptions scheduleOptions = scheduler.EXPR(config.scheduler_expression());
            scheduleOptions.name(String.valueOf(schedulerId));
            scheduleOptions.canRunConcurrently(false);
            scheduler.schedule(config, scheduleOptions);
            logger.info("####Scheduler add####");
            ScheduleOptions scheduleOptionsNow = scheduler.NOW();
            scheduler.schedule(config, scheduleOptionsNow);
    }
    private void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerId));
    }

}
