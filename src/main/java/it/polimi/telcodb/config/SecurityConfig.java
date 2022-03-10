package it.polimi.telcodb.config;

import it.polimi.telcodb.security.AuthenticationSecurityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSecurityHandler authenticationSecurityHandler;


    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(
                        "/",
                        "/registerUser",
                        "/openUserHomePage",
                        "/registerEmployee",
                        "/showServicePackageInfo",
                        "/openBuyServicePackagePage",
                        "/selectServicePackageToBuy",
                        "/openConfirmationPage",
                        "/resetBuyServicePackagePage",
                        "/registerBeforeBuyingServicePackage",
                        "/registerUserBeforePurchase"
                ).permitAll()
                .antMatchers(
                        "/openEmployeePage",
                        "/createFixedPhoneService",
                        "/createMobilePhoneService",
                        "/createFixedInternetService",
                        "/createMobileInternetService",
                        "/createValidityPeriod",
                        "/createOptionalProduct",
                        "/createServicePackage",
                        "/openCreateSPPage",
                        "/openInspectionPage"
                ).hasAuthority("ADMIN")
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(authenticationSecurityHandler)

                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/performLogout"))
                .logoutSuccessUrl("/").permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }

}
