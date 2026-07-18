package ApiFleetManager.FleetManager.Tipo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_TIPO")
public class TipoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO")
    private Integer idTipo;
    @Column(name = "NOMBRETIPO")
    private String nombretipo;
    @Column(name = "FK_MODELO")
    private Integer fkModelo;
}
