package com.sila.config;

import com.sila.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/admin/**").hasAnyRole("ADMIN").
                    requestMatchers("/api/**").authenticated().anyRequest().permitAll()).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class).csrf(csrf-> {
              try {
                csrf.disable().cors(cors->cors.configurationSource(corsConfigrationSource()));
              } catch (Exception e) {
                throw new UnauthorizedException("AuthorizedException : "+e);
              }
            });
        return http.build();
    }

    private CorsConfigurationSource corsConfigrationSource() {
        return request -> {
            CorsConfiguration cfg=new CorsConfiguration();
            cfg.setAllowedOrigins(List.of("http://localhost:3000","https://next-js-wow-now.vercel.app",
                    "http://localhost:3030","http://192.168.1.8:3030","http://192.168.1.8:3000","*"));
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(List.of("Authorization"));
            cfg.setMaxAge(3600L);
            return cfg;
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
