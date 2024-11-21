
package com.connect.jpa.config;

// Importações estáticas para simplificar código
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

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

/**
 * Classe de configuração central de segurança para a aplicação.
 * 
 * Responsabilidades:
 * - Definir regras de autenticação e autorização
 * - Configurar filtros de segurança
 * - Gerenciar provedor de autenticação
 * 
 * Anotações importantes:
 * - @Configuration: Indica que a classe contém configurações de bean
 * - @EnableWebSecurity: Habilita a configuração de segurança web do Spring
 * - @EnableMethodSecurity: Permite usar anotações de segurança em métodos
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,   // Permite @PreAuthorize e @PostAuthorize
    securedEnabled = true,   // Habilita anotação @Secured
    jsr250Enabled = true     // Permite anotações @RolesAllowed
)
public class SecurityConfig { 

    // Array de endpoints públicos que não requerem autenticação
    // IMPORTANTE: Manter atualizado conforme necessidades do projeto
    private static final String[] PUBLIC_ENDPOINTS = {
        "/auth/generateToken",   // Endpoint para geração de token
        "/auth/register",        // Endpoint de registro de usuário
        "/error",                // Página de erro padrão
        "/v3/api-docs/**",       // Documentação da API
        "/h2-console/**",        // Console do H2 (banco de dados em memória)
        "/swagger-ui/**",        // Interface do Swagger
        "/swagger-ui.html",      // Página principal do Swagger
        "/webjars/**",           // Recursos web para Swagger
        "/swagger-resources/**"  // Recursos de configuração do Swagger
    };

    // Filtro JWT para autenticação baseada em token
    private final JwtAuthFilter authFilter;

    /**
     * Construtor para injeção do filtro JWT.
     * 
     * @param authFilter Filtro de autenticação JWT
     */
    public SecurityConfig(JwtAuthFilter authFilter) { 
        this.authFilter = authFilter; 
    }
    
    /**
     * Configura o codificador de senhas.
     * 
     * @return PasswordEncoder configurado com BCrypt
     * 
     * Notas:
     * - BCryptPasswordEncoder é seguro para ambiente de produção
     * - Sem parâmetro, usa força padrão (custo de 10)
     */

    /**
     * Cria o serviço de detalhes do usuário.
     * 
     * @param repository Repositório de usuários
     * @param passwordEncoder Codificador de senhas
     * @return Serviço de detalhes do usuário
     */
    @Bean
    UserDetailsService userDetailsService(
        UserInfoRepository repository, 
        PasswordEncoder passwordEncoder
    ) { 
        return new UserInfoService(repository, passwordEncoder); 
    }

    /**
     * Configuração principal da cadeia de filtros de segurança.
     * 
     * Defines:
     * - Quais endpoints são públicos
     * - Regras de autorização
     * - Configurações de sessão
     * - Filtros de segurança
     * 
     * @param http Configurador de segurança HTTP
     * @param authenticationProvider Provedor de autenticação
     * @return Cadeia de filtros de segurança
     * @throws Exception Em caso de erro de configuração
     */
    @Bean
    SecurityFilterChain filterChain(
        HttpSecurity http, 
        AuthenticationProvider authenticationProvider
    ) throws Exception { 
        return http
            // Configuração de autorização de requisições
            .authorizeHttpRequests(authz -> authz
                // Libera endpoints públicos
                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                
                // Libera endpoints do H2 Console
                .requestMatchers(toH2Console()).permitAll()
                
                // Qualquer outro endpoint requer autenticação
                .anyRequest().authenticated()
            )
            
            // Desabilita proteção de frame para H2 Console
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable())
            )
            
            // Configurações de CSRF (Cross-Site Request Forgery)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console()) // Ignora CSRF para H2
                .disable() // Desabilitado para simplicidade (ajustar em produção)
            )
            
            // Configuração de gerenciamento de sessão
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Ideal para APIs REST
            )
            
            // Registra provedor de autenticação
            .authenticationProvider(authenticationProvider)
            
            // Adiciona filtro JWT antes do filtro padrão de autenticação
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            
            // Habilita autenticação HTTP básica
            .httpBasic(withDefaults())
            .build();
    }

    /**
     * Cria provedor de autenticação baseado em DAO (Data Access Object).
     * 
     * @param userDetailsService Serviço de detalhes do usuário
     * @param passwordEncoder Codificador de senhas
     * @return Provedor de autenticação
     * 
     * Funcionalidades:
     * - Carrega usuário do banco de dados
     * - Verifica credenciais
     */
    @Bean
    AuthenticationProvider authenticationProvider(
        UserDetailsService userDetailsService, 
        PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    /**
     * Gerencia o processo de autenticação.
     * 
     * @param config Configuração de autenticação
     * @return Gerenciador de autenticação
     * @throws Exception Em caso de erro de configuração
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
}