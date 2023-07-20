package com.fastfood.fastfood.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import com.fastfood.fastfood.entity.Customer;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;
import com.fastfood.fastfood.service.AuthService;

@Provider
@Auth
@Priority(Priorities.AUTHENTICATION + 10)
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    @AuthQualifier(AuthType.CUSTOMER)
    private AuthService authService;

    @Context
    private HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            final String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization == null || !authorization.toLowerCase().startsWith("basic")) {
                throw new AuthException("Invalid authorization: " + authorization);
            }
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoder = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoder, StandardCharsets.UTF_8);
            final String[] params = credentials.split(":", 2);
            String login = params[0];
            String password = params[1];

            Customer authUser = (Customer) authService.getByLoginAndPassword(login, password);
            if (authUser.getDeactivated() != null) {
                throw new AuthException("");
            }
            String user_id = String.valueOf(authUser.getId());
            requestContext.getHeaders().add("user_id", user_id);
            requestContext.setProperty("user_id", user_id);
        } catch (AuthException e) {
            throw new IOException(e);
        } catch (DaoException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
}
