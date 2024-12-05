package com.abernathyclinic.patient.security;

import com.abernathyclinic.patient.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filter for validating JSON Web Tokens (JWT) in incoming HTTP requests.
 * <p>
 * This filter ensures that each request contains a valid JWT in the `Authorization` header.
 * If the token is valid, the user's authentication is added to the {@link SecurityContextHolder}.
 * </p>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    /**
     * Constructs a new {@code JwtAuthenticationFilter} with the specified {@link JwtService}.
     *
     * @param jwtService the service responsible for validating and extracting information from JWTs.
     */
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Processes incoming HTTP requests to validate the JWT in the `Authorization` header.
     * <p>
     * If the JWT is valid, the user's authentication is set in the {@link SecurityContextHolder}.
     * If the JWT is invalid or missing, the request either proceeds unmodified or returns a 401 Unauthorized response.
     * </p>
     *
     * @param request     the incoming HTTP request.
     * @param response    the outgoing HTTP response.
     * @param filterChain the filter chain for further processing the request.
     * @throws ServletException if an error occurs while processing the request.
     * @throws IOException      if an I/O error occurs while processing the request.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        if (jwtService.isTokenValid(jwt)) {
            String username = jwtService.extractUsername(jwt);
            // Add authentication to the security context
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, List.of()); // Add roles if necessary
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

}
