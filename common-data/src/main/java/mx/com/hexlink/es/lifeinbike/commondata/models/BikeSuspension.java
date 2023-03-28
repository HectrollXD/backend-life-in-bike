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
@Table(name = "data_bike_suspensions")
@EqualsAndHashCode(callSuper = true)
public class BikeSuspension extends ControlOfData{
    public enum SuspensionType{
        HYDRAULIC,
        PNEUMATIC,
        SPRING
    }



    @Column(name = "suspension_type", nullable = false, length = 10)
    private SuspensionType suspensionType;

    @Column(name = "brand", nullable = false, length =  32)
    private String brand;

    @Column(name = "model", nullable = false, length =  32)
    private String model;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bike_id", referencedColumnName = "id")
    private Bike bike;



    // Getters and Setters
    public String getBrand() {
        return WordUtils.capitalize(this.brand);
    }

    public void setBrand(String brand) {
        this.brand = brand.trim().toUpperCase();
    }

    public String getModel() {
        return WordUtils.capitalize(this.model);
    }

    public void setModel(String model) {
        this.model = model.trim().toUpperCase();
    }
}
