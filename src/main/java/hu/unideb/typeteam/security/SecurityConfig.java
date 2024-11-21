package hu.unideb.typeteam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery
                ("select user_id, pw, active from users where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery
                ("SELECT u.user_id, r.role " +
                        "FROM users_roles ur " +
                        "JOIN roles r ON ur.role_id = r.role_id " +
                        "JOIN users u ON ur.user_id = u.user_id " +
                        "WHERE u.user_id = ?");
        return jdbcUserDetailsManager;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurator ->
                        configurator
                                .requestMatchers("/", "/register/**", "css/**", "images/**")
                                .permitAll()
                                .requestMatchers("/dashboard/**")
                                .hasAuthority("ROLE_ADMIN")  // Explicitly use "ROLE_" prefix if you want it
                                .anyRequest()
                                .authenticated()
                ).formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/authenticateUser")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }
}
