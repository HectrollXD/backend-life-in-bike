package mx.com.hexlink.es.lifeinbike.commondata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "catalog_questions")
@EqualsAndHashCode(callSuper = true)
public class SecurityQuestion extends ControlOfData{
    @Column(
        name = "question", nullable = false, unique = true,
        updatable = false, length = 256, insertable = false
    )
    private String question;
}
