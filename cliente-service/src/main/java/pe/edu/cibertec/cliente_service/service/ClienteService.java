package pe.edu.cibertec.cliente_service.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.cliente_service.dto.ClienteDTO;
import pe.edu.cibertec.cliente_service.mapper.ClienteMapper;
import pe.edu.cibertec.cliente_service.model.Cliente;
import pe.edu.cibertec.cliente_service.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(guardado);
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public ClienteDTO obtenerPorUserId(Long userId) {
        return clienteRepository.findByUserId(userId)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado para userId " + userId));
    }

    public ClienteDTO actualizarCliente(Long id, ClienteDTO dto) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        existente.setNombres(dto.getNombres());
        existente.setApellidos(dto.getApellidos());
        existente.setDireccion(dto.getDireccion());
        existente.setTelefono(dto.getTelefono());

        return clienteMapper.toDTO(clienteRepository.save(existente));
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}