package mx.com.hexlink.es.lifeinbike.commondata.models;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "data_users")
@EqualsAndHashCode(callSuper = true)
public class Account extends ControlOfData{
    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username = null;

    @Column(name = "password", nullable = false, length = 256)
    private String password = null;

    @Column(name = "email", nullable = false, unique = true, length = 256)
    private String email = null;

    @Column(name = "is_disabled", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDisabled = false;

    @OneToOne(mappedBy = "account")
    private Person personData;

    @OneToMany(mappedBy = "account")
    private Set<Answer> securityAnswers;

    @OneToMany(mappedBy = "account")
    private Set<Bike> bikes;



    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", isDisabled='" + getIsDisabled() + "'" +
            ", personData='" + getPersonData() + "'" +
            ", securityAnswers='" + getSecurityAnswers() + "'" +
            ", bikes='" + getBikes() + "'" +
            "}";
    }
}
