package com.adobe.aem.my.wknd.site.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition(name="A scheduled task",
                           description = "Simple demo for cron-job like task with properties")
public  @interface SchedulerConfig {

        @AttributeDefinition(name = "Cron-job name")
    
        String scheduler_name() default "scheduler";

        @AttributeDefinition(name = "Cron-job expression")
    
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name = "Concurrent task",
                             description = "Whether or not to schedule this task concurrently")
        boolean scheduler_concurrent() default false;

        @AttributeDefinition(name = "A parameter",
                             description = "Can be configured in /system/console/configMgr")
        String myParameter() default "";
 }

