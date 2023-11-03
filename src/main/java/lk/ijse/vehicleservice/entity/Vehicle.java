package lk.ijse.vehicleservice.entity;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String vehicle_brand;
    @Column(nullable = false)
    private String vehicle_category;
    @Column(nullable = false)
    private String fuel_type;
    @Column(nullable = false)
    private String hybrid_OR_nonHybrid;
    @Column(nullable = false)
    private String fuel_usage;
    @Column(nullable = false)
    @Lob
    private byte[]vehicle_img;
    @Column(nullable = false)
    private int seatCapacity;
    @Column(nullable = false)
    private String  vehicle_type;
    @Column(nullable = false)
    private String driver_name;
    @Column(nullable = false)
    private String driver_contact;
    @Column(nullable = false)
    @Lob
    private byte[] driver_license_image;

}
