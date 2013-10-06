package fr.kissy.overseer.config;

import fr.kissy.module.rest.config.AbstractResourceConfig;
import fr.kissy.overseer.resource.EndpointResource;
import fr.kissy.overseer.service.EndpointService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guillaume Le Biller
 */
@Configuration
public class ServiceConfig extends AbstractResourceConfig {
    @Bean
    public EndpointService endpointService() {
        return new EndpointService();
    }
}
