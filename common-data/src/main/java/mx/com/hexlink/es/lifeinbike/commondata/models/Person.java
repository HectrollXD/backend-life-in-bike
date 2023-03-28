package mx.com.hexlink.es.lifeinbike.commondata.models;

import java.time.LocalDateTime;
import org.apache.commons.text.WordUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "data_persons")
@EqualsAndHashCode(callSuper = true)
public class Person extends ControlOfData{
    @Column(name = "names", nullable = false, length = 128)
    private String names;

    @Column(name = "f_lastname", length = 64)
    private String firstLastname;

    @Column(name = "s_lastname", length = 64)
    private String secondLastname;

    @Column(name = "birth_date", columnDefinition = "timestamp")
    private LocalDateTime birthDate;

    @Column(name = "telephone", length = 10)
    private String telephone;



    @OneToOne(mappedBy = "person")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", unique = true, nullable = false)
    private Account account;



    // Getters and setters
    public String getNames() {
        return WordUtils.capitalize(firstLastname);

    }

    public void setNames(String names) {
        this.names = names.trim().toUpperCase();;
    }

    public String getFirstLastname() {
        return WordUtils.capitalize(this.firstLastname);
    }

    public void setFirstLastname(String firstLastname) {
        this.firstLastname = firstLastname.trim().toUpperCase();;
    }

    public String getSecondLastname() {
        return WordUtils.capitalize(this.secondLastname);
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastname = secondLastname.trim().toUpperCase();;
    }
}
