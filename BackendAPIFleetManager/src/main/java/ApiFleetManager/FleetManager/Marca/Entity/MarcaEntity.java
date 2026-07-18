package ApiFleetManager.FleetManager.Marca.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_MARCA")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARCA")
    private Integer idMarca;
    @Column(name = "NOMBREMARCA")
    private String nombremarca;
}
