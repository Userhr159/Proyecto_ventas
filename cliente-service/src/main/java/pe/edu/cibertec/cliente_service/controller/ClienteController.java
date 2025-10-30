package pe.edu.cibertec.cliente_service.controller;




import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.cliente_service.dto.ClienteDTO;
import pe.edu.cibertec.cliente_service.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.crearCliente(dto));
    }

    @GetMapping("listar")
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<ClienteDTO> obtenerPorUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(clienteService.obtenerPorUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }
}