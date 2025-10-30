package pe.edu.cibertec.cliente_service.mapper;



import org.springframework.stereotype.Component;
import pe.edu.cibertec.cliente_service.dto.ClienteDTO;
import pe.edu.cibertec.cliente_service.model.Cliente;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) return null;
        return ClienteDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .userId(cliente.getUserId())
                .build();
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;
        return Cliente.builder()
                .idCliente(dto.getIdCliente())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .userId(dto.getUserId())
                .build();
    }
}