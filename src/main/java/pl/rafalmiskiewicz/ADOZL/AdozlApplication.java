package pl.rafalmiskiewicz.ADOZL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import pl.rafalmiskiewicz.ADOZL.rest.JetFilter;

import java.util.Collections;

@SpringBootApplication
class AdozlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdozlApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JetFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/*"));
        return  filterRegistrationBean;
    }
}
