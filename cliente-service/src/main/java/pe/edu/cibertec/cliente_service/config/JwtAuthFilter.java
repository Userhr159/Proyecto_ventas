package pe.edu.cibertec.cliente_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.edu.cibertec.cliente_service.client.AuthClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthClient authClient;

    public JwtAuthFilter(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                Map<String, Object> result = authClient.validateToken("Bearer " + token);

                if (Boolean.TRUE.equals(result.get("valid"))) {
                    String username = (String) result.get("username");
                    String role = (String) result.get("role");

                    // ✅ Evitamos duplicar el prefijo ROLE_
                    if (!role.startsWith("ROLE_")) {
                        role = "ROLE_" + role;
                    }

                    var auth = new UsernamePasswordAuthenticationToken(
                            username, null, List.of(new SimpleGrantedAuthority(role))
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (Exception e) {
                System.out.println("❌ Token inválido o error al validar con auth-service: " + e.getMessage());
            }
        }

        chain.doFilter(request, response);
    }
}
