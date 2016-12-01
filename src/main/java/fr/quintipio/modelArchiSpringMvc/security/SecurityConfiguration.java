package fr.quintipio.modelArchiSpringMvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository tokenRepository;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
        auth.inMemoryAuthentication()
                .withUser("admin@admin.fr")
                .password("$2a$12$scBFVdLYj2EKdjl9524tSecyrRh556w.TS.Pw5vgQWISNowpheqSW")//adminadmin
                .roles("ADMIN" , "USER");
        auth.inMemoryAuthentication()
                .withUser("test@test.fr")
                .password("$2a$12$YdwU0KeZ94MLzaUBtHdTfOaDl/fBMyZy4o59WsYj2KuheHon1ggt2")//testtest
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/static/**").permitAll()
                .antMatchers( "/webjars/**").permitAll()
                .antMatchers( "/create").permitAll()
                .antMatchers("/list", "/delete-user-*","edit-user-*").access(" hasRole('ADMIN')").anyRequest().hasRole("ADMIN")
                .antMatchers("/update").hasAnyRole("ADMIN","USER","DBA") .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("sso")
                .passwordParameter("password")
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400)
                .and()
                .csrf()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/Access_Denied");
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
