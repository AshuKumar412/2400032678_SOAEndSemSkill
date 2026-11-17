
package soa.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    // Pre-generated token for this experiment
    private static final String VALID_TOKEN =
            "TOURIST-ACCESS-2026";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String token = request.getHeader("Token");

        // Validate token before database access
        if (token == null || !VALID_TOKEN.equals(token)) {

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.setContentType(
                    MediaType.APPLICATION_JSON_VALUE
            );

            response.getWriter().write(
                    "{\"status\":500,"
                    + "\"error\":\"Internal Server Error\","
                    + "\"message\":\"Invalid or missing token\"}"
            );

            return false;
        }

        // Valid token: allow the request to continue
        return true;
    }
}
