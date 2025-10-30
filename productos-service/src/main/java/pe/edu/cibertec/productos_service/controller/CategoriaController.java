package pe.edu.cibertec.productos_service.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.productos_service.model.Categoria;
import pe.edu.cibertec.productos_service.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nueva = categoriaService.crearCategoria(categoria);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping("listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Categoría no encontrada");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            Categoria actualizada = categoriaService.actualizarCategoria(id, categoria);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        try {
            categoriaService.eliminarCategoria(id);
            return ResponseEntity.ok("Categoría eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo eliminar: " + e.getMessage());
        }
    }
}