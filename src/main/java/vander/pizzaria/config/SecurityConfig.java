package vander.pizzaria.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vander.pizzaria.service.UsuarioService;

import java.lang.reflect.Method;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private UsuarioService usuarioService;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http

                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/*").permitAll()

                        .anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());


        return http.build();
    }
    @Bean
    static PasswordEncoder psEncode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    protected void auth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService);
    }
//    }


   /* @Bean
    UserDetailsService user() {
        UserDetails user = User.builder()
                .username("VanderleiOBrabo")
                .password(psEncode().encode("123"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

}
