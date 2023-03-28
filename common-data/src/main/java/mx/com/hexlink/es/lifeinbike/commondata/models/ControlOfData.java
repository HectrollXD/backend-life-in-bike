package mx.com.hexlink.es.lifeinbike.commondata.models;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@MappedSuperclass
public class ControlOfData {
    // Id del registro.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;
    
    // Describe cuando fue creado el registro.
    @Column(
        name = "crated_at",
        nullable = false,
        updatable = false,
        columnDefinition = "timestamp"
    )
    private LocalDateTime createdAt;

    // Describe cuando fue modificado el registro.
    @Column(
        name = "last_modifed",
        nullable = false,
        updatable = true,
        columnDefinition = "timestamp"
    )
    private LocalDateTime lastModifed;


    // Constructors
    public ControlOfData(){
        this.createdAt = LocalDateTime.now();
        this.lastModifed = LocalDateTime.now();
    }
}
