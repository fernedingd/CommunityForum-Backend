package de.community.forum.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ApiBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    /**
     * Handles AuthenticationException. Return HTTP Status Code 401 with more details and real name.
     *
     * @param request  Request
     * @param response Response
     * @param authEx   AuthenticationException
     * @throws IOException ioException
     */
    @Override
    public void commence
    (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

    /**
     * Set the real name.
     *
     * @throws Exception exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Community Forum");
        super.afterPropertiesSet();
    }
}