package br.com.bighead.springbootkafka.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j

public class ReadDbPropertiesPostProcessor implements EnvironmentPostProcessor {

    private static final String[] PROPERTIES = {"consumer.status"};
    private static final String PROPERTY_SOURCE_NAME = "databaseProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var propertySource = new HashMap<String, Object>();

        log.atInfo().log("blaaaaaaaaaaaaaaaaaaaa");
        try {
            // the following db connections properties must be defined in application.properties
            var ds = DataSourceBuilder
                    .create()
                    .username(environment.getProperty("spring.datasource.username"))
                    .password(environment.getProperty("spring.datasource.password"))
                    .url(environment.getProperty("spring.datasource.url"))
                    .driverClassName("org.mariadb.jdbc.Driver")
                    .build();

            try (var connection = ds.getConnection();
                 // suppose you have a config table storing the properties name/value pair
                 var preparedStatement = connection.prepareStatement("SELECT value FROM properties WHERE name = ?")) {
                for (int i = 0; i < PROPERTIES.length; i++) {
                    String configName = PROPERTIES[i];
                    preparedStatement.setString(1, configName);

                    var rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        propertySource.put(configName, rs.getString("value"));
                    }

                    preparedStatement.clearParameters();
                }
            }
            environment.getPropertySources().addFirst(new MapPropertySource(PROPERTY_SOURCE_NAME, propertySource));
            application.setEnvironment(environment);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
