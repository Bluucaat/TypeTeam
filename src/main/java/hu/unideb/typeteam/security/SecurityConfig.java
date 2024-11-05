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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery
                ("select user_id, pw, active from members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery
                ("SELECT m.user_id, r.role " +
                        "FROM members_roles mr " +
                        "JOIN roles r ON mr.role_id = r.role_id " +
                        "JOIN members m ON mr.user_id = m.user_id " +
                        "WHERE m.user_id = ?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurator ->
                configurator
                        .requestMatchers("/", "/register/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        ).formLogin(form -> form.loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                .defaultSuccessUrl("/", true)
                .permitAll());
        return http.build();
    }
}
