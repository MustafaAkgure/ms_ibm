package com.ibm.project.configuration;


import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableJpaRepositories("com.ibm.project.repository")
@EntityScan("com.ibm.project.model")
public class MsIbmDatabaseConfiguration  implements EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsIbmDatabaseConfiguration.class);

    private RelaxedPropertyResolver dataSourcePropertyResolver;

    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(name = "dataSource", destroyMethod = "")
    @Primary
    @ConditionalOnExpression("#{!environment.acceptsProfiles('cloud') && !environment.acceptsProfiles('heroku') && !environment.acceptsProfiles('dokku')}")
    public DataSource dataSource() {
        if (dataSourcePropertyResolver.getProperty("url") == null && dataSourcePropertyResolver.getProperty("databaseName") == null) {
            LOGGER.error("Your database connection pool configuration is incorrect! The application" +
                            " cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUrl(dataSourcePropertyResolver.getProperty("url"));
        basicDataSource.setUsername(dataSourcePropertyResolver.getProperty("username"));
        basicDataSource.setPassword(dataSourcePropertyResolver.getProperty("password"));
        basicDataSource.setDriverClassName(dataSourcePropertyResolver.getProperty("driverClassName"));

        return basicDataSource;
    }
}
