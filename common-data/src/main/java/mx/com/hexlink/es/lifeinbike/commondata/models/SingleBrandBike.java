package mx.com.hexlink.es.lifeinbike.commondata.models;

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
@Table(name = "data_single_brand_bike")
@EqualsAndHashCode(callSuper = true)
public class SingleBrandBike extends ControlOfData{
    // Define la marca de la bici.
    @Column(name = "brand", length = 32)
    private String brand;
    
    // Define el modelo de la bici.
    @Column(name = "model", length = 32)
    private String model;

    // Define el color de la bici.
    @Column(name = "color", length = 64)
    private String color;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;



    // Getters and setters.
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand.trim().toUpperCase();
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model.trim().toUpperCase();
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color.trim().toUpperCase();
    }
}