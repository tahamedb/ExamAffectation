package com.taha.Affectation.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/projects/{id}/assign").hasRole("MANAGER")
                        .requestMatchers("/employees").hasAnyRole("MANAGER", "TECH_LEAD")
                        .requestMatchers("/api/employees").hasAnyRole("DEV", "TEST", "DEVOPS")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/google")
                        .successHandler(oAuth2AuthenticationSuccessHandler())
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
                String email = (String) attributes.get("email");

                List<GrantedAuthority> authorities = new ArrayList<>();
                if (email.equals("sarahwhite30031003@gmail.com")) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                } else if (email.equals("techlead@gmail.com")) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_TECH_LEAD"));
                } else if (email.equals("dev@gmail.com")) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_DEV"));
                } else if (email.equals("test@gmail.com")) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_TEST"));
                } else if (email.equals("devops@gmail.com")) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_DEVOPS"));
                }

                OAuth2AuthenticationToken updatedToken = new OAuth2AuthenticationToken(
                        oauthToken.getPrincipal(), authorities, oauthToken.getAuthorizedClientRegistrationId());

                SecurityContextHolder.getContext().setAuthentication(updatedToken);

                response.sendRedirect("/employees"); // Redirect to the home page or any other desired page
            }
        };
    }
}