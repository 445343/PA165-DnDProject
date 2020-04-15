package cz.fi.muni.PA165.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@ComponentScan("cz.fi.muni.PA165.persistence.dao")
@Import(DBConfig.class)
public class DAOconfig {

}
