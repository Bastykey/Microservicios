package cl.duoc.MicroservicioVenta.Controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.duoc.MicroservicioVenta.Model.Venta;
import cl.duoc.MicroservicioVenta.Service.VentaService;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        List<Venta> ventas = ventaService.buscarTodo();
        return ventas.isEmpty() ? 
            ResponseEntity.noContent().build() : 
            ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVenta(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("ID de venta inválido");
        }
        
        return ventaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Venta venta) {
        try {
            if (venta.getFechaventa() == null || venta.getFechaventa().isAfter(LocalDate.now())) {
                return ResponseEntity.badRequest().body("Fecha de venta inválida");
            }
            
            Venta ventaNueva = ventaService.GuardarVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(ventaNueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al guardar venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        //Validacion simple
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("ID de venta inválido");
            }
            
            if (!ventaService.ExisteVenta(id)) {
                return ResponseEntity.notFound().build();
            }
            
            ventaService.EliminarVenta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al eliminar venta");
        }
    }

    @GetMapping("/por-fecha")
     public ResponseEntity<?> buscarPorFecha(@RequestParam LocalDate fecha) {
        try {
            if (fecha == null || fecha.isAfter(LocalDate.now())) {
                return ResponseEntity.badRequest().body("Fecha inválida");
            }
            
            List<Venta> ventas = ventaService.BuscarPorFecha(fecha);
            return ventas.isEmpty() ? 
                ResponseEntity.noContent().build() : 
                ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al buscar por fecha");
        }
    }
}