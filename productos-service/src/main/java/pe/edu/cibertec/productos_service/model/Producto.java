package pe.edu.cibertec.productos_service.model;
import jakarta.persistence.*;
import lombok.*;



import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    private String nombre;

    private String marca;

    private Double precio;

    private Integer stock;

    private String descripcion;
    // Campo para la URL de la imagen (String simple)
    private String urlImagenPrincipal;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}