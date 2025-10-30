package com.proyecto.pagos_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @Column(name = "id_venta", nullable = false)
    private Long idVenta; // ID de la venta proveniente del microservicio de ventas

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false, length = 30)
    private String metodo; // Ej: "Efectivo", "Tarjeta", "Yape", "Plin"

    @Column(nullable = false, length = 20)
    private String estado; // Ej: "Pendiente", "Completado", "Fallido"
}