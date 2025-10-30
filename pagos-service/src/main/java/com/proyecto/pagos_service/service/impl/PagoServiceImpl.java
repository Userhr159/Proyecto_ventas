package com.proyecto.pagos_service.service.impl;

import com.proyecto.pagos_service.model.Pago;
import com.proyecto.pagos_service.repository.PagoRepository;
import com.proyecto.pagos_service.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    @Override
    public Pago registrarPago(Pago pago) {
        pago.setFecha(LocalDateTime.now());

        if (pago.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor a cero");
        }

        if (pago.getEstado() == null || pago.getEstado().isEmpty()) {
            pago.setEstado("Pendiente");
        }

        return pagoRepository.save(pago);
    }

    @Override
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public List<Pago> listarPorVenta(Long idVenta) {
        return pagoRepository.findByIdVenta(idVenta);
    }

    @Override
    public Pago actualizarEstado(Long idPago, String nuevoEstado) {
        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el pago con ID: " + idPago));

        pago.setEstado(nuevoEstado);
        return pagoRepository.save(pago);
    }
}
