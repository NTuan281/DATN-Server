package com.myproject.main.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.myproject.main.jwt.JwtAuthenticationEntryPoint;
import com.myproject.main.jwt.JwtTokenProvider;
import com.myproject.main.filter.JwtTokenFilterConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross-site request forgery)
        http.csrf().disable();

        // Enable CORS and configure it
        http.cors();

        // Cho phép đường dẫn đăng ký không yêu cầu xác thực
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        // Permit access to the authentication endpoint
        http.authorizeRequests().antMatchers("/api/auth/login").permitAll();
        
        http.authorizeRequests().antMatchers("/api/users").permitAll();
        
        http.authorizeRequests().antMatchers("/execute").permitAll();
        
        

//      Secure other endpoints with JWT
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN") // Example: Requires ADMIN role for GET /api/users
                .anyRequest().authenticated().and().apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Disable unnecessary authentication mechanisms and session creation
        http.httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Customize authentication entry point
        http.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        
     
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
