package mx.com.hexlink.es.lifeinbike.commondata.models;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "data_accounts")
@EqualsAndHashCode(callSuper = true)
public class Account extends ControlOfData{
    @OneToOne(mappedBy = "account")
    private Person personData;

    @OneToMany(mappedBy = "account")
    private Set<Answer> securityAnswers;

    @OneToMany(mappedBy = "account")
    private Set<Bike> bikes;



    @Override
    public String toString() {
        return "{" +
            ", personData='" + getPersonData() + "'" +
            ", securityAnswers='" + getSecurityAnswers() + "'" +
            ", bikes='" + getBikes() + "'" +
            "}";
    }
}
