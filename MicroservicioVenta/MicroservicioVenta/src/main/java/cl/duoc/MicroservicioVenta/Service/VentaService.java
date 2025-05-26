package cl.duoc.MicroservicioVenta.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.duoc.MicroservicioVenta.Model.Venta;
import cl.duoc.MicroservicioVenta.Repository.VentaRepository;
import java.time.LocalDate;





@Service
public class VentaService {
    private final VentaRepository ventaRepository = null;


    public List<Venta> buscarTodo() {
        return ventaRepository.findAll();
    }

    public List<Venta> BuscarTodaVenta(){
        return ventaRepository.findAll();

    }
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }
    public Venta GuardarVenta(Venta venta){
        return ventaRepository.save(venta);
    }
    public List<Venta> BuscarPorFecha(LocalDate fecha) {
        return ventaRepository.findByFecha(fecha);
    }
    public void EliminarVenta(long idventa){
    ventaRepository.deleteById(idventa);
    }

    public boolean ExisteVenta(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'ExisteVenta'");
    }
}





