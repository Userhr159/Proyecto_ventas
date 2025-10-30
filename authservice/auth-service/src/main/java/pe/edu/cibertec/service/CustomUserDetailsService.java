package pe.edu.cibertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.model.UserEntity;
import pe.edu.cibertec.repository.UserRepository;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 🔹 Normalizamos el rol para evitar duplicar "ROLE_"
        String roleName = user.getRole().name();
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName;
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roleName.replace("ROLE_", "")) // ✅ Spring Security agrega el prefijo internamente
                .build();
    }
}