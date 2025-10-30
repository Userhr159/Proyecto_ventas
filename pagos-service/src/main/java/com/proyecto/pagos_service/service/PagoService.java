package com.proyecto.pagos_service.service;

import com.proyecto.pagos_service.model.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    Pago registrarPago(Pago pago);

    List<Pago> listarPagos();

    Optional<Pago> obtenerPorId(Long id);

    List<Pago> listarPorVenta(Long idVenta);

    Pago actualizarEstado(Long idPago, String nuevoEstado);
}
