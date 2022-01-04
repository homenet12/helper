package damo.helper.config.security;

import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import damo.helper.service.MemberService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final MemberService userService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/", "/login","/join", "/assets/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.successHandler(new SimpleUrlAuthenticationSuccessHandler())
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/questions")
				.failureHandler(loginFailHandlr())
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
			.and()
				.exceptionHandling()
				.accessDeniedPage("/error")
			.and()
				.rememberMe()
				.key("rememberKey") 
				.rememberMeParameter("rememberMe")
				.tokenValiditySeconds(86400 * 30)
				.userDetailsService(userService)
				.authenticationSuccessHandler(rememberSuccessMeHandlr());
    }
    
    private AuthenticationSuccessHandler rememberSuccessMeHandlr() {
    	return new SimpleUrlAuthenticationSuccessHandler("/questions");
    }
    
    private AuthenticationFailureHandler loginFailHandlr() {
    	return new LoginFailHandlr();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    
}
