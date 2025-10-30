package pe.edu.cibertec.productos_service.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.productos_service.model.Producto;
import pe.edu.cibertec.productos_service.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        Producto nuevo = productoService.crearProducto(producto);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(@RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return ResponseEntity.ok(productoService.listarPorCategoria(categoriaId));
        }
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productoService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto actualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo eliminar: " + e.getMessage());
        }
    }
}