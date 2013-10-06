package fr.kissy.overseer.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
@Configuration("databaseConfig")
public class DatabaseConfig {
    @Value("${module.database.dataSource.driverClass}")
    private String driverClass;
    @Value("${module.database.dataSource.jdbcUrl}")
    private String jdbcUrl;
    @Value("${module.database.dataSource.user}")
    private String user;
    @Value("${module.database.dataSource.password}")
    private String password;
    @Value("${module.database.dataSource.maxPoolSize}")
    private Integer maxPoolSize;

    @Bean(destroyMethod = "close")
    public DataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverClass);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setMaxPoolSize(maxPoolSize);
        return comboPooledDataSource;
    }
}
