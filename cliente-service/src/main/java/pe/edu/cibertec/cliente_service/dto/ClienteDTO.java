package pe.edu.cibertec.cliente_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long idCliente;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private Long userId;
}