package rs.maxbet.worldofgamecraft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfiguration {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration() {
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((httpSecurityCsrfConfigurer) -> {
            httpSecurityCsrfConfigurer.disable();
        }).authorizeHttpRequests((authz) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authz.anyRequest()).authenticated();
        }).addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }
}
