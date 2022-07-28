package com.example.natube.security;

import com.example.natube.security.filter.FormLoginFilter;
import com.example.natube.security.provider.FormLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
        http.csrf().disable();

        http.authorizeRequests()
                // image,css폴더를 login없이 허용
                .antMatchers("/image/**").permitAll()
                .antMatchers("/css/**").permitAll()
                // user에 관련된 api 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // 그 외 나머지 요청은 '인증'받아야함
                .anyRequest().authenticated()
                .and()
                // 로그인 기능
//                .formLogin()
//                // 로그인 View 제공 (GET /user/login) -> 프론트에서 처리
//                .loginPage("/user/login")
//                // 로그인 처리 (POST /user/login)
//                .loginProcessingUrl("/user/login")
//                // 로그인 처리 후 성공 시 ("/")
//                .defaultSuccessUrl("/")
//                //로그인 처리 후 실패 시 URL
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
                // 로그아웃 기능
                .logout()
                // 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                // "접근 불가" 페이지 URL 설정
                .accessDeniedPage("/forbidden.html");
        http.addFilterAt(formLoginFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Configuration
    public static class webConfig implements WebMvcConfigurer{
        @Override
        public void addCorsMappings(CorsRegistry registry){

            registry.addMapping("/**") //cors를 적용할 url패턴 정의
                    .allowedOrigins("*") //모든 origins(출처, Url의 protocol, domain, port) 허락
                    .allowedMethods("*") // 모든 method 허락
                    .maxAge(3000);      // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱

        }
    }

//    @Bean
//    public FormLoginProvider formLoginProvider() {
//        return new FormLoginProvider(userDetailsService, passwordEncoder());
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(formLoginProvider());
//    }

}
