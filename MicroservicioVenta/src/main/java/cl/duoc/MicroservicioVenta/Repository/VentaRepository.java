package cl.duoc.MicroservicioVenta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.MicroservicioVenta.Model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByFecha(LocalDate fecha);
}