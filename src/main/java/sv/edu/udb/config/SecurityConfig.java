package sv.edu.udb.config;

import sv.edu.udb.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Bean para la encriptación de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Proveedor de autenticación que conecta el servicio de UserDetails con el cifrador de contraseñas
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Configuración del AuthenticationManager, necesaria para autenticar usuarios manualmente si es requerido
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuración principal de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Puedes habilitar CSRF si lo necesitas en otro contexto
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Solo ADMIN puede acceder a estas rutas
                        .requestMatchers("/vendedor/**").hasRole("VENDEDOR") // Solo VENDEDOR puede acceder a estas rutas
                        .requestMatchers("/cliente/**").hasRole("CLIENTE") // Solo CLIENTE puede acceder a estas rutas
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página personalizada de login
                        .permitAll() // Permitimos acceso a todos a la página de login
                )
                .logout(logout -> logout
                        .permitAll() // Permitimos que cualquiera pueda cerrar sesión
                );

        return http.build();
    }
}