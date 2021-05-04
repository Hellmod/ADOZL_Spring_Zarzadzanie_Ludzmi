package pl.rafalmiskiewicz.ADOZL.springsecurityjwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test2").authenticated()
                .antMatchers("/test3").hasRole("ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }
}
