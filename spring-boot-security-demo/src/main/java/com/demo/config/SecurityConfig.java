package com.demo.config;

import com.demo.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author xiafb
 * @date Created in 2020/5/20 10:15
 * description SpringSecurity核心配置类
 * modified By
 * version  1.0
 */
@Configuration
@EnableWebSecurity
//开启权限注解,默认是关闭的
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthAccessDeniedHandler authAccessDeniedHandler;

    @Autowired
    private UserAuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Autowired
    private UserAuthenticationProvider authenticationProvider;

    @Autowired
    private UserLoginFailureHandler loginFailureHandler;

    @Autowired
    private UserLoginSuccessHandler loginSuccessHandler;

    @Autowired
    private UserLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private UserPermissionEvaluator permissionEvaluator;


    /**
     * 加密方式
     * @return 加密方式实例
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义权限解析器
     * @return DefaultWebSecurityExpressionHandler
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不进行权限验证的请求或资源从配置文件中读取
                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()
                //其他需要登录后才能访问
                .anyRequest().authenticated()
                .and()
                //配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(authenticationEntryPointHandler)
                .and()
                //配置登录地址
                .formLogin()
                .loginProcessingUrl("/login/userLogin")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .logoutUrl("/login/userLoginOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(authAccessDeniedHandler)
                .and()
                .cors()
                .and()
                .csrf().disable();
        super.configure(http);
    }
}
