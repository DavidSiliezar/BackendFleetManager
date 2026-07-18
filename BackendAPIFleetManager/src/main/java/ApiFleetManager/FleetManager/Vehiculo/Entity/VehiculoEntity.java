package ApiFleetManager.FleetManager.Vehiculo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_VEHICULO")
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VEHICULO")
    private Integer idVehiculo;
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "KILOMETRAJE")
    private Double kilometraje;
    @Column(name = "NUMEROVIN")
    private String numerovin;
    @Column(name = "TARJETACIRCULACION")
    private String tarjetacirculacion;
    @Column(name = "FK_TIPO")
    private Integer fkTipo;
    @Column(name = "FK_ESTADO")
    private Integer fkEstado;
}
