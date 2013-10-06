package fr.kissy.overseer.config;

import fr.kissy.module.rest.config.AbstractResourceConfig;
import fr.kissy.overseer.resource.EndpointResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guillaume Le Biller
 */
@Configuration
public class ResourceConfig extends AbstractResourceConfig {
    @Bean
    public EndpointResource endpointResource() {
        return new EndpointResource();
    }
}
