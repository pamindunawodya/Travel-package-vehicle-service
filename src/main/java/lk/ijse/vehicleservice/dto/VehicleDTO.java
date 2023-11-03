package lk.ijse.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class VehicleDTO {
    private long id;
    private String vehicle_brand;
    private String vehicle_category;
    private String fuel_type;
    private String hybrid_OR_nonHybrid;
    private String fuel_usage;
    private byte[]vehicle_img;
    private int seatCapacity;
    private String vehicle_type;
    private String driver_name;
    private String driver_contact;
    private byte[] driver_license_image;


}
