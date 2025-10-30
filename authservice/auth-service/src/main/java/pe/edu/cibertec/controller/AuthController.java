package pe.edu.cibertec.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.config.JwtUtils;
import pe.edu.cibertec.dto.AuthRequest;
import pe.edu.cibertec.dto.AuthResponse;
import pe.edu.cibertec.dto.RegisterRequest;
import pe.edu.cibertec.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7); // quitar "Bearer "
            if (!jwtUtils.validateToken(jwt)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
            }

            String username = jwtUtils.extractUsername(jwt);
            String role = jwtUtils.extractRole(jwt);

            return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "username", username,
                    "role", role
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido o expirado");
        }
    }
}


