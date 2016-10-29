package bookmarks;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableAutoConfiguration
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(WebSecurityConfig.class);
    @Value("${application.home-page}")
    private String homePage = "/index";

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Override

    public void configure(WebSecurity web) throws Exception {
              // 设置不拦截规则
            web.ignoring().antMatchers("/assets/**","/css/**", "/img/**","/js/**","/audio/**");
            web.ignoring().antMatchers("/autoconfig","/beans","/health","/configprops","/dump","/env","/info","/metrics","/mappings","/trace");
            web.ignoring().antMatchers("/404","/500","/403");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().accessDecisionManager(accessDecisionManager());


        http.authorizeRequests().antMatchers("/login","/logout").permitAll();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl(homePage)
                .successHandler(loginSuccessHandler)
                .permitAll()
                .and()
                .rememberMe();
        /*
        * .and()
		.rememberMe().tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(1209600);
		*/

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");

        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
        auth.userDetailsService(myUserDetailsService);
        auth.eraseCredentials(false);
    }

    @SuppressWarnings("rawtypes")
    @Bean(name = "accessDecisionManager")
    public AccessDecisionManager accessDecisionManager() {
        return new MyAccessDecisionManager();
    }


}