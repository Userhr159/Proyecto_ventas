package pe.edu.cibertec.cliente_service.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    private String direccion;

    private String telefono;

    @Column(nullable = false, unique = true)
    private Long userId; // 🔗 ID del usuario proveniente del Auth-Service
}