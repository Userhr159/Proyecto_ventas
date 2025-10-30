package com.proyecto.pagos_service.repository;

import com.proyecto.pagos_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByIdVenta(Long idVenta);

    List<Pago> findByEstado(String estado);
}
