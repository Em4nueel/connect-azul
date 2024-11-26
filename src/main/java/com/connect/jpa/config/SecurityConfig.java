
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,   // Permite o uso das anotações @PreAuthorize e @PostAuthorize nos métodos
    securedEnabled = true,   // Habilita o uso da anotação @Secured
    jsr250Enabled = true     // Permite o uso da anotação @RolesAllowed
)
public class SecurityConfig implements WebMvcConfigurer {  // Implementa WebMvcConfigurer para configurar o CORS

    // Definindo os endpoints públicos que não requerem autenticação
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

    // Construtor da classe, injetando o filtro JWT
    public SecurityConfig(JwtAuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    /**
     * Adiciona configurações de CORS, permitindo requisições de origens específicas.
     * Aqui configuramos para que as requisições do frontend (localhost:5173) sejam permitidas.
     * 
     * @param registry Usado para registrar as configurações de CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Configura CORS para todos os endpoints
                .allowedOrigins("http://localhost:5173") // Permite origens do frontend (ajustar conforme necessário)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite métodos HTTP específicos
                .allowedHeaders("*") // Permite qualquer cabeçalho
                .allowCredentials(true); // Permite o envio de cookies (ex: tokens JWT)
    }

    /**
     * Configuração principal da cadeia de filtros de segurança.
     * Define as regras de autorização, gerenciamento de sessões e os filtros de segurança a serem aplicados.
     * 
     * @param http Configuração da segurança HTTP
     * @param authenticationProvider Provedor de autenticação
     * @return A cadeia de filtros de segurança
     * @throws Exception Se ocorrer algum erro de configuração
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception {
        return http
            // Configura regras de autorização de requisições
            .authorizeHttpRequests(authz -> authz
                // Libera acesso para os endpoints públicos definidos acima
                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                
                // Libera acesso para o console do H2
                .requestMatchers(toH2Console()).permitAll()
                
                // Qualquer outro endpoint exige autenticação
                .anyRequest().authenticated()
            )
            
            // Configura a exibição de cabeçalhos HTTP
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable())  // Desabilita a proteção de frame para o H2
            )
            
            // Configurações de CSRF (Cross-Site Request Forgery)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console())  // Ignora CSRF para o H2
                .disable()  // Desabilita CSRF (não recomendado em produção sem ajustes)
            )
            
            // Gerenciamento de sessões (usado para APIs REST)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Usa política sem estado (ideal para APIs)
            )
            
            // Configura o provedor de autenticação
            .authenticationProvider(authenticationProvider)
            
            // Adiciona o filtro JWT antes do filtro de autenticação padrão
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            
            // Habilita autenticação HTTP básica
            .httpBasic(withDefaults()) 
            .build();
    }

    /**
     * Cria e configura o provedor de autenticação.
     * Este provedor é usado para carregar os detalhes do usuário a partir do banco de dados.
     * 
     * @param userDetailsService Serviço de detalhes do usuário
     * @param passwordEncoder Codificador de senhas
     * @return O provedor de autenticação
     */
    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);  // Define o serviço que carrega os detalhes do usuário
        authenticationProvider.setPasswordEncoder(passwordEncoder);        // Define o codificador de senha
        return authenticationProvider;
    }

    /**
     * Configura o gerenciamento de autenticação.
     * 
     * @param config Configuração de autenticação
     * @return O gerenciador de autenticação
     * @throws Exception Se ocorrer algum erro de configuração
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); // Obtém o gerenciador de autenticação
    } 
}
