package fr.kissy.overseer.application;

import fr.kissy.module.rest.application.AbstractJavaApplicationInitializer;
import fr.kissy.overseer.config.ApplicationConfig;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class JavaApplicationInitializer extends AbstractJavaApplicationInitializer {
    /**
     * @inheritDoc
     */
    @Override
    protected Class getJavaConfiguration() {
        return ApplicationConfig.class;
    }
}
