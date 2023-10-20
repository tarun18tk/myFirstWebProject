package com.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//With the help of spring security
// All URLs are protected from any unautharised access
// We get a Login Page

@Configuration
public class SpringSecurityConfiguration {

    // These are some predefined functions here used to give a default
    // password and user to the application.

    // @Bean is used because this is Spring Security and in the @Configuration class
    // we need to specify
    // what methods are beans managed by spring.
    @Bean
    public InMemoryUserDetailsManager createMemoryUserDetailsManager() {

        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails userDetails = createNewUser("Tarun", "admin", passwordEncoder);

        UserDetails userDetails2 = createNewUser("Karan", "dummy", passwordEncoder);

        return new InMemoryUserDetailsManager(userDetails, userDetails2);

    }

    private UserDetails createNewUser(String username, String password, Function<String, String> passwordEncoder) {
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
                .roles("USER", "ADMIN").build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // To use h2-console
    // we need to enable frames, which is something h2-websites use
    // enable CSRF

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();

    }
}
