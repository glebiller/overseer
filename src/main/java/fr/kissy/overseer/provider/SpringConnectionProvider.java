package fr.kissy.overseer.provider;

import fr.kissy.overseer.config.ApplicationConfig;
import org.quartz.utils.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class SpringConnectionProvider implements ConnectionProvider {
    @Autowired
    private DataSource dataSource;

    /**
     * @inheritDoc
     */
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void shutdown() throws SQLException {

    }

    /**
     * @inheritDoc
     */
    @Override
    public void initialize() throws SQLException {
        ApplicationConfig.getContext().getAutowireCapableBeanFactory().autowireBean(this);
    }
}
