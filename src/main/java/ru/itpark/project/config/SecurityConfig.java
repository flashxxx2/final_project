//package ru.itpark.project.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // чтобы иметь возможность писать аннотации, проверяющие права доступа над методами
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests() // сверху вниз применяются правила
//                .antMatchers("/index").permitAll()
//                .antMatchers("/api/**").permitAll()
//                // если будут картинки и вы захотите дать доступ к ним
//                // .antMatchers("/media/*").permitAll() // * - только до /
//                // /media/img/a.jpg -> **
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .defaultSuccessUrl("/index", true)
//                .permitAll()
//                .and()
//                .logout().permitAll();
//    }
//}