package com.controlu.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Configura a cadeia de filtros de segurança para o aplicativo.
     *
     * <p>Este método define as configurações de segurança para as requisições HTTP
     * usando o {@link HttpSecurity}. Ele desativa o CSRF (Cross-Site Request Forgery),
     * define a política de criação de sessões como {@link SessionCreationPolicy#STATELESS},
     * e especifica as permissões de acesso para diferentes endpoints e métodos HTTP.
     * Além disso, adiciona um filtro de autenticação customizado antes do filtro
     * {@link UsernamePasswordAuthenticationFilter}.</p>
     *
     * @param httpSecurity O objeto {@link HttpSecurity} que permite configurar a segurança HTTP.
     * @return Um {@link SecurityFilterChain} que representa a configuração de segurança definida.
     * @throws Exception Se ocorrer algum erro durante a configuração.
     *
     *
     * <p></p>
     * <p>Explicação de cada configuração:</p>
     *
     * <ul>
     *     <li><strong>csrf.disable():</strong> Desativa a proteção CSRF, que é normalmente usada
     *     para evitar ataques de Cross-Site Request Forgery, mas pode ser desativada em APIs REST
     *     que utilizam tokens para autenticação.</li>
     *
     *     <li><strong>sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS):</strong>
     *     Define a política de criação de sessões como stateless (sem estado), ou seja, o Spring Security
     *     não armazenará a sessão do usuário. Ideal para APIs baseadas em tokens JWT.</li>
     *
     *     <li><strong>authorizeHttpRequests:</strong> Configura as permissões de acesso para diferentes
     *     métodos HTTP e endpoints.
     *     <ul>
     *        <li><As roles indicam que a requisição requer o nível de acesso especificado, por exemplo <strong>POST /api/**:</strong>
     *        Requer a role "ADM" para qualquer requisição POST que corresponda ao padrão "/api/**".</li>
     *        <li><strong>anyRequest().authenticated():</strong> Qualquer outra requisição exige que o
     *        usuário esteja autenticado.</li>
     *     </ul>
     *     </li>
     *
     *     <li><strong>addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class):</strong>
     *     Adiciona o filtro customizado {@code securityFilter} antes do filtro padrão de autenticação
     *     {@link UsernamePasswordAuthenticationFilter}, permitindo personalizações no processo de autenticação.</li>
     * </ul>
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf ->  csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADM")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADM")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/api/usuario/**").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/api/acesso/**").hasRole("OPERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/aluno/verificarSeEstaRegistrado/**").hasRole("OPERADOR")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
