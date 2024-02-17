package com.example.UserWishList.config;

import com.example.UserWishList.Mapper.UserNameToUserDetails;
import com.example.UserWishList.Mapper.UserToUserDetails;
import com.example.UserWishList.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserNameToUserDetails();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //   return http.authorizeHttpRequests(auth->auth.requestMatchers("/rooms").hasRole("ADMIN").requestMatchers("/").authenticated()).formLogin(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable).build()
        return http.authorizeHttpRequests(auth->auth.
                requestMatchers("/api/vo/user/register","/swagger-ui/**","/swagger-ui.html" ,"/javainuse-openapi/**").permitAll().requestMatchers("/api/vo/user/**").hasRole("ADMIN").requestMatchers("/api/vo/wishListItem/**").authenticated()).formLogin(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
    }
}
