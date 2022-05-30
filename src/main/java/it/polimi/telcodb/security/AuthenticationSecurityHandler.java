package it.polimi.telcodb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationSecurityHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // SavedRequestAwareAuthenticationSuccessHandler makes use of the saved request stored in the session.
        // After a successful login, users will be redirected to the URL saved in the original request.
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        HttpSession session = request.getSession();

        if (session != null) {
            if (role.contains("ADMIN")) {
                String redirectUrl = "/openEmployeePage";
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
