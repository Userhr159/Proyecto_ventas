package pe.edu.cibertec.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.config.JwtUtils;
import pe.edu.cibertec.dto.AuthRequest;
import pe.edu.cibertec.dto.AuthResponse;
import pe.edu.cibertec.dto.RegisterRequest;
import pe.edu.cibertec.model.UserEntity;
import pe.edu.cibertec.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token);
    }
}
