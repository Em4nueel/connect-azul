package com.connect.jpa.config;
import static //
org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.AuthenticationProvider; 
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; 
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; 
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.http.SessionCreationPolicy; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.web.SecurityFilterChain; 
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.connect.jpa.filter.JwtAuthFilter;
import com.connect.jpa.repository.UserInfoRepository;
import com.connect.jpa.service.UserInfoService;


import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
    private final JwtAuthFilter authFilter; 
    public SecurityConfig(JwtAuthFilter authFilter) { 
        this.authFilter = authFilter; 
    }
    
    /**
     * Todos os metodos/atributos dentro de uma interface são abstratos
     * então é necessario retirar o "public" da frente do metodo 
     */

    // User Creation 
    @Bean
    UserDetailsService userDetailsService(UserInfoRepository repository, PasswordEncoder passwordEncoder) { 
        return new UserInfoService(repository, passwordEncoder); 
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception { 
        return http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/auth/generateToken", "/auth/register").permitAll()
                .requestMatchers("/swagger-ui/**",
                    "/swagger-resources/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/api/clinicas/"
                    ).authenticated()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/auth/hello").authenticated()
                .requestMatchers("/auth/logout").authenticated()
            )
            .authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .authorizeHttpRequests(auth -> auth .requestMatchers(toH2Console()).permitAll())
            .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
            .httpBasic(withDefaults()).csrf((csrf) -> csrf.ignoringRequestMatchers(toH2Console()))
            .httpBasic(withDefaults()).csrf((csrf) -> csrf.disable())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
}