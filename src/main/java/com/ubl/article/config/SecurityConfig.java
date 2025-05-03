package com.ubl.article.config;

import com.ubl.article.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/api/**") // allow API POST/DELETE without CSRF token
            .ignoringRequestMatchers(PathRequest.toH2Console())
        )
        .headers(headers -> headers
            .frameOptions(frame -> frame.sameOrigin())
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/login", "/article/**").permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/**").hasRole("API")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .defaultSuccessUrl("/admin")
            .permitAll()
        )
        .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic Auth
        .logout(logout -> logout.permitAll());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepo) {
    return username -> userRepo.findByUsername(username)
      .map(user -> User.withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(user.getRole())
        .build())
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

