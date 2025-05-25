package cl.duoc.MicroservicioVenta.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.duoc.MicroservicioVenta.Model.Venta;
import cl.duoc.MicroservicioVenta.Service.VentaService;
import java.time.LocalDate;

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
    public ResponseEntity<Venta> buscarVenta(@PathVariable Long id) {
        return ventaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        Venta ventaNueva = ventaService.GuardarVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaNueva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            ventaService.EliminarVenta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/por-fecha")
    
    public ResponseEntity<List<Venta>> buscarPorFecha(@RequestParam LocalDate fecha) {
        List<Venta> ventas = ventaService.BuscarPorFecha(fecha);
        return ventas.isEmpty() ? 
            ResponseEntity.noContent().build() : 
            ResponseEntity.ok(ventas);
    }
}