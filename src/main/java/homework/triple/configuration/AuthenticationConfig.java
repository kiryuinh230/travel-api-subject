package homework.triple.configuration;

import homework.triple.configuration.filter.JwtTokenFilter;
import homework.triple.global.exception.CustomAuthenticationEntryPoint;
import homework.triple.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.secret-key}")
	private String key;

	private final MemberService memberService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/member/join", "/member/login", "/h2-console/**").permitAll()
			.antMatchers("/**").authenticated()

			.and()
			.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))

			.frameOptions().sameOrigin()

			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(new JwtTokenFilter(key, memberService), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
	}
}
