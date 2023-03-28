package mx.com.hexlink.es.lifeinbike.commondata.models;

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
@Table(name = "data_address")
@EqualsAndHashCode(callSuper = true)
public class Address extends ControlOfData{
    @Column(name = "street_name", nullable = false, length = 128)
    private String streetName;

    @Column(name = "ext_number", nullable = false, length = 16)
    private String extNumber;

    @Column(name = "int_number", length = 128)
    private String intNumber;

    @Column(name = "city", nullable = false, length = 256)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 8)
    private String postalCode;

    @Column(name = "state", nullable = false, length = 128)
    private String state;

    @Column(name = "addresReferences", length = 256)
    private String addresReferences;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person person;

    //




    // Getters and Setters
    public String getStreetName() {
        return WordUtils.capitalize(this.streetName);
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName.trim().toUpperCase();
    }

    public void setExtNumber(String extNumber) {
        this.extNumber = extNumber.trim().toUpperCase();
    }

    public void setIntNumber(String intNumber) {
        this.intNumber = intNumber.trim().toUpperCase();
    }

    public String getCity() {
        return WordUtils.capitalize(this.city);
    }

    public void setCity(String city) {
        this.city = city.trim().toUpperCase();
    }

    public String getState() {
        return WordUtils.capitalize(this.state);
    }

    public void setState(String state) {
        this.state = state.trim().toUpperCase();
    }

    public String getAddresReferences() {
        return WordUtils.capitalize(this.addresReferences);
    }

    public void setAddresReferences(String addresReferences) {
        this.addresReferences = addresReferences.trim().toUpperCase();
    }
}
