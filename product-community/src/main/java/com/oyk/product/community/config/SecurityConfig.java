package com.oyk.product.community.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        web.ignoring().mvcMatchers("/css/**", "/js/**","/img/**","/error/**","/lib/**");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        http
                .authorizeRequests( authorize -> authorize

                        .mvcMatchers(
                                "/members/join"
                        )
                        .anonymous()
                        .mvcMatchers(
                                "/articles/**"
                        )
                        .permitAll()
                        .mvcMatchers(
                                "/adm/**"
                        )
                        .hasRole("ADMIN")
                        .anyRequest()
                        .denyAll()
                )

                .formLogin()
                .loginPage("/members/login")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("loginId")
                .passwordParameter("loginPw")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
