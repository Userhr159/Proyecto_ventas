package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
