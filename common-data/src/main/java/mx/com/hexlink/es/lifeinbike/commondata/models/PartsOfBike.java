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
@Table(name = "data_bike_parts")
@EqualsAndHashCode(callSuper = true)
public class PartsOfBike extends ControlOfData{
    // Marca y el modelo del cuadro.
    @Column(name = "frame", nullable = false, length = 256)
    private String frame;

    // Marca y modelo de la tijera.
    @Column(name = "fork", nullable = false, length = 256)
    private String fork;

    // Marca y modelo del manillar.
    @Column(name = "bars", nullable = false, length = 256)
    private String bars;

    // Marca y modelo de la maza delantera.
    @Column(name = "front_hub", nullable = false, length = 256)
    private String frontHub;

    // Marca y modelo de la maza trasera.
    @Column(name = "rear_hub", nullable = false, length = 256)
    private String rearHub;

    // Marca y modelo del rin delantero.
    @Column(name = "front_rim", nullable = false, length = 256)
    private String frontRim;

    // Marca y modelo del rin trasero.
    @Column(name = "rear_rim", nullable = false, length = 256)
    private String rearRim;

    // Marca y modelo de las llantas.
    @Column(name = "tires", nullable = false, length = 256)
    private String tires;

    // Marca y modelo de las cámaras.
    @Column(name = "tubes", nullable = false, length = 256)
    private String tubes;

    // Marca y modelo del poste.
    @Column(name = "steam", nullable = false, length = 256)
    private String steam;

    // Marca y modelo del asiento.
    @Column(name = "seat", nullable = false, length = 256)
    private String seat;

    // Marca y modelo del tubo de asinto.
    @Column(name = "seat_tube", nullable = false, length = 256)
    private String seatTube;

    // Marca y modelo del plato.
    @Column(name = "sprocket", nullable = false, length = 256)
    private String sprocket;

    // Marca y modelo de la cadena.
    @Column(name = "chain", nullable = false, length = 256)
    private String chain;

    // Marca y modelo de las vielas.
    @Column(name = "crank", nullable = false, length = 256)
    private String crank;

    // Marca y modelo de los pedales.
    @Column(name = "pedals", nullable = false, length = 256)
    private String pedals;

    // Marca y modelo de los baleros del telescopio (internas).
    @Column(name = "headset", nullable = false, length = 256)
    private String headset;

    // Marca y modelo de los baleros del centro (tazas).
    @Column(name = "brackets", nullable = false, length = 256)
    private String brackets;



    // Join to bike table.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bike_id", nullable = false, referencedColumnName = "id")
    private Bike bike;
}