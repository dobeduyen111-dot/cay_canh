package ceb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/error").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/cart/**", "/checkout/**").authenticated()
                        .requestMatchers("/product/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {

                        boolean isAdmin = authentication.getAuthorities().stream()
                                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

                        if (isAdmin) {
                                response.sendRedirect("/admin/dashboard"); 
                        } else {
                                response.sendRedirect("/");     
                        }
                        })
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) -> {
                                if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
                                res.sendError(401); 
                                } else {
                                res.sendRedirect("/login");
                                }
                        })
                        .accessDeniedPage("/error/403")
                        )
                .logout(logout -> logout
                .logoutUrl("/auth/logout")          
                .logoutSuccessUrl("/")                
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
        return http.build();
    }
        @Bean
        public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        }
}

