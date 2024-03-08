package com.example.labjee.config;

import com.example.labjee.helpers.UsersLoggedInSingleton;
import com.example.labjee.services.UserDetailsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/logout").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .successHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                    // Tydzień 2 - wzorzec Singleton - zwiększenie licznika
                    UsersLoggedInSingleton.getInstance().userLoggedIn();
                    // Tydzień 2 - wzorzec Singleton - zwiększenie licznika - koniec
                    response.sendRedirect("/");
                })
                .failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
                    if (exception != null)
                    {
                        final FlashMap flashMap = new FlashMap();
                        flashMap.put("failure", "Zły login lub hasło.");
                        
                        final FlashMapManager flashMapManager = new SessionFlashMapManager();
                        flashMapManager.saveOutputFlashMap(flashMap, request, response);
                    }
                    
                    response.sendRedirect("/login");
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        if (request.getHeader("Referer").endsWith(request.getHeader("Host") + "/settings")) {
                            FlashMap flashMap = new FlashMap();
                            flashMap.put("success", "Operacja zakończona powodzeniem.");

                            FlashMapManager flashMapManager = new SessionFlashMapManager();
                            flashMapManager.saveOutputFlashMap(flashMap, request, response);

                            response.sendRedirect("/login");
                        } else {
                            response.sendRedirect(request.getHeader("Referer"));
                        }
                        // Tydzień 2 - wzorzec Singleton - zmniejszenie licznika
                        UsersLoggedInSingleton.getInstance().userLoggedOut();
                        // Tydzień 2 - wzorzec Singleton - zmniejszenie licznika - koniec
                        super.onLogoutSuccess(request, response, authentication);
                    }
                })
                .invalidateHttpSession(true)
            )    
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable);
        
        return http.build();
    }
}

