//package cz.fi.muni.PA165.rest.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = {"cz.fi.muni.PA165.rest.controllers", "cz.fi.muni.PA165.rest.assemblers"})
//public class RestBeanConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(customJackson2HttpMessageConverter());
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter(){
//
//    }
//
//}
