package cz.fi.muni.PA165.service.config;

import cz.fi.muni.PA165.persistence.dao.DAOconfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DAOconfig.class)
@ComponentScan(basePackages = {"cz.fi.muni.PA165.service", "cz.fi.muni.PA165.service.facade"})
public class ServiceConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
