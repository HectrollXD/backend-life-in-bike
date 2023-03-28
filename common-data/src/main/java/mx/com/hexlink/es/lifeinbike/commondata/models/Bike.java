package mx.com.hexlink.es.lifeinbike.commondata.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "data_bikes")
@EqualsAndHashCode(callSuper = true)
public class Bike extends ControlOfData{
    public enum TypeOfBike{
        BMX,
        MTB,
        MOUNTAIN,
        ROUTE,
        URBAN
    }

    public enum BracketType{
        MID,
        EURO,
        AMERICAN,
        SPANISH
    }



    // Define si la bicicleta está ensamblada pieza a pieza.
    @Column(
        name = "with_multiple_brands", nullable = false, columnDefinition = "boolean default false"
    )
    private Boolean withMultipleBrands;

    // Define el rodado de la bicicleta
    @Column(name = "size", nullable = false)
    private Float size;

    // Define el tipo de bici.
    @Enumerated
    @Column(name = "type_of_bike", nullable = false, length = 8)
    private TypeOfBike typeOfBike;

    // Define el tipo de centro de la bici.
    @Enumerated
    @Column(name = "bracket_type", nullable = false, length = 8)
    private BracketType bracketType;

    // Define si la bici tiene suspencíón.
    @Column(name = "with_suspension", nullable = false, columnDefinition = "boolean default false")
    private Boolean withSuspension;

    // Define si la bici tiene frenos.
    @Column(name = "with_breaks", nullable = false, columnDefinition = "boolean default false")
    private Boolean withBreaks;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @OneToOne(mappedBy = "bike")
    private SingleBrandBike singleBrand;

    @OneToOne(mappedBy = "bike")
    private PartsOfBike partsOfBike;

    @OneToOne(mappedBy = "bike")
    private BikeBreak breakType;

    @OneToMany(mappedBy = "bike")
    private Set<BikeImage> bikeImage;
}
