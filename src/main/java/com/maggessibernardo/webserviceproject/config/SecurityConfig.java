package com.maggessibernardo.webserviceproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.maggessibernardo.webserviceproject.services.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Definir o AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/admin/**").hasRole("ADMIN")    // Acesso restrito a ADMIN
                .requestMatchers("/user/**").hasRole("USER")      // Acesso restrito a USER
                .requestMatchers("/h2-console/**").permitAll()     // Permite acesso ao H2 Console sem autenticação
                .anyRequest().authenticated()                     // Qualquer outra requisição deve estar autenticada
            )
            .formLogin(withDefaults())  // Permite o login via formulário (com comportamento padrão)
            .httpBasic(withDefaults())  // Autenticação básica (por exemplo, curl/Postman)
            .headers(headers -> extracted(headers)  // Permite que o H2 Console seja exibido em um iframe
            );

        return http.build();  // Necessário para construir a configuração do filtro
    }

	@SuppressWarnings("removal")
	private HeadersConfigurer<HttpSecurity> extracted(HeadersConfigurer<HttpSecurity> headers) {
		return headers
		    .frameOptions().sameOrigin();
	}

    // Bean para codificar a senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
