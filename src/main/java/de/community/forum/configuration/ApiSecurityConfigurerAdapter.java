package de.community.forum.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private ApiBasicAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * The Constructor.
     *
     * @param authenticationEntryPoint ApiBasicAuthenticationEntryPoint
     */
    @Autowired
    ApiSecurityConfigurerAdapter(ApiBasicAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * Writes user credentials into memory.
     * TODO Get user credentials from DB.
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN");
    }

    /**
     * Configures Spring HTTP Security. Sets 'basic authentication' as security mechanism to protect the API.
     * Permits some endpoints to be accessed without credentials.
     *
     * @param http Spring HttpSecurity to configure
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/hello-world").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * Creates new PasswordEncoder. Encodes base64 decodes Strings.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}