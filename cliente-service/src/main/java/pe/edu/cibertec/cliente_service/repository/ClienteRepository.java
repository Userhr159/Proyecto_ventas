package pe.edu.cibertec.cliente_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.cliente_service.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUserId(Long userId);
}