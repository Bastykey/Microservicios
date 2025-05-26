package cl.duoc.MicroservicioVenta.Model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "VENTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    private Long id_venta;
    @Column (name = "RUT_USUARIO" , nullable = false, length = 15)
    private String rutusuario;
    @Column(name =   "FECHA_VENTA", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaventa;

}
