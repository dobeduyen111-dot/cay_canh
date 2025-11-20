package ceb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(p -> p.requestMatchers("/", "/auth/login", "/webfonts/*", "/images/*", "/css/*", "/js/*").permitAll()
            .anyRequest().authenticated())
            .oauth2Login(p -> p.loginPage("/auth/login").defaultSuccessUrl("/", true))
            .logout(p -> p.logoutUrl("/auth/logout").logoutSuccessUrl("/auth/login?logout"));
        return http.build();
    }
    
}
