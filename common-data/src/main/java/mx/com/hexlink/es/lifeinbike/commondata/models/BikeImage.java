package mx.com.hexlink.es.lifeinbike.commondata.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "catalog_bike_images")
@EqualsAndHashCode(callSuper = true)
public class BikeImage extends ControlOfData{
    @Column(name = "image_extension", nullable = false, length = 8)
    private String imageExtension;

    @Lob
    @Column(name = "image", nullable = false, length = 1024)
    private byte[] image;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bike_id", referencedColumnName = "id", nullable = false)
    private Bike bike;
}
