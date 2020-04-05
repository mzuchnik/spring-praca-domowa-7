package pl.mzuchnik.springpracadomowa7;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://remotemysql.com:3306/8qtDLLcK8w")
                .username("8qtDLLcK8w")
                .password("3zDdiygIdt")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        return new JdbcTemplate(getDataSource());
    }
}
