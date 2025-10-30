package com.proyecto.pagos_service.controller;

import com.proyecto.pagos_service.model.Pago;
import com.proyecto.pagos_service.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PagoController {

    private final PagoService pagoService;

    // Registrar un nuevo pago
    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.registrarPago(pago);
        return ResponseEntity.ok(nuevoPago);
    }

    // Listar todos los pagos
    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(pagoService.listarPagos());
    }

    // Buscar pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable Long id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar pagos por venta
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<Pago>> listarPorVenta(@PathVariable Long idVenta) {
        return ResponseEntity.ok(pagoService.listarPorVenta(idVenta));
    }

    // Actualizar estado del pago
    @PutMapping("/{idPago}/estado")
    public ResponseEntity<Pago> actualizarEstado(
            @PathVariable Long idPago,
            @RequestParam String nuevoEstado
    ) {
        Pago actualizado = pagoService.actualizarEstado(idPago, nuevoEstado);
        return ResponseEntity.ok(actualizado);
    }
}
