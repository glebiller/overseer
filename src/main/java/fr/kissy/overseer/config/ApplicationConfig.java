package fr.kissy.overseer.config;

import fr.kissy.module.rest.config.RestConfig;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Guillaume <lebiller@fullsix.com>
 */
@Configuration("applicationConfig")
@Import({DatabaseConfig.class, ResourceConfig.class, ServiceConfig.class, RestConfig.class})
@PropertySource("classpath:fr/kissy/overseer/common.properties")
public class ApplicationConfig implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        schedulerFactory.initialize("fr/kissy/overseer/quartz.properties");
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    public static ApplicationContext getContext() {
        return CONTEXT;
    }
}
