package lk.ijse.vehicleservice.api;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ModelMapper mapper;

    @PostMapping
    public ResponseUtil saveVehicle(@RequestParam("id") Long id,
                                    @RequestParam("vehicle_brand") String vehicle_brand,
                                    @RequestParam("vehicle_category") String vehicle_category,
                                    @RequestParam("fuel_type") String fuel_type,
                                    @RequestParam("hybrid_OR_nonHybrid") String hybrid_OR_nonHybrid,
                                    @RequestParam("fuel_usage") String fuel_usage,
                                    @RequestParam("vehicle_img") MultipartFile vehicle_img,
                                    @RequestParam("seatCapacity") int seatCapacity,
                                    @RequestParam("vehicle_type") String vehicle_type,
                                    @RequestParam("driver_name") String driver_name,
                                    @RequestParam("driver_contact") String driver_contact,
                                    @RequestParam("driver_license_image") MultipartFile driver_license_image) throws IOException {
        VehicleDTO vehicle = new VehicleDTO(id,  vehicle_brand,vehicle_category, fuel_type, hybrid_OR_nonHybrid, fuel_usage, vehicle_img.getBytes(), seatCapacity, vehicle_type, driver_name, driver_contact, driver_license_image.getBytes());
        System.out.println(vehicle.getVehicle_brand());
        System.out.println(vehicle.getVehicle_img());
        vehicleService.addVehicle(vehicle);
        return new ResponseUtil("200", "Vehicle AddedSucessfull", null);
    }


    @PutMapping
    public ResponseUtil updateVehicle(@RequestParam("id") Long id,
                                      @RequestParam("vehicle_category") String vehicle_category,
                                      @RequestParam("vehicle_brand") String vehicle_brand,
                                      @RequestParam("fuel_type") String fuel_type,
                                      @RequestParam("hybrid_OR_nonHybrid") String hybrid_OR_nonHybrid,
                                      @RequestParam("fuel_usage") String fuel_usage,
                                      @RequestParam("vehicle_img") MultipartFile vehicle_img,
                                      @RequestParam("seatCapacity") int seatCapacity,
                                      @RequestParam("vehicle_type") String vehicle_type,
                                      @RequestParam("driver_name") String driver_name,
                                      @RequestParam("driver_contact") String driver_contact,
                                      @RequestParam("driver_license_image") MultipartFile driver_license_image) throws IOException {


        Vehicle existsVehicle = vehicleService.findById(id);

        if (existsVehicle != null) {
            existsVehicle.setVehicle_brand(vehicle_brand);
            existsVehicle.setVehicle_category(vehicle_category);
            existsVehicle.setFuel_type(fuel_type);
            existsVehicle.setHybrid_OR_nonHybrid(hybrid_OR_nonHybrid);
            existsVehicle.setFuel_usage(fuel_usage);
            existsVehicle.setVehicle_img(vehicle_img.getBytes());
            existsVehicle.setSeatCapacity(seatCapacity);
            existsVehicle.setVehicle_type(vehicle_type);
            existsVehicle.setDriver_name(driver_name);
            existsVehicle.setDriver_contact(driver_contact);
            existsVehicle.setDriver_license_image(driver_license_image.getBytes());

            vehicleService.updateVehicle(existsVehicle);
            return new ResponseUtil("200", "Updated Vehicle Data Sucessfull!", null);
        } else {
            return new ResponseUtil("404", "Vehicle not found", null);
        }
    }

    @DeleteMapping(params = "id")
    public ResponseUtil deleteVehicle(@RequestParam Long id){

        vehicleService.deleteVehicele(id);
        return new ResponseUtil("200" ,id+"Deleted SucessFull",null);

    }
    @GetMapping
    public ResponseUtil getAllVehicle(){
        ArrayList<VehicleDTO>vehicleDTOS=vehicleService.getAllVehicles();

            return new ResponseUtil("200", "Show All Vehicles", vehicleDTOS);
    }

    @GetMapping(value = "api/search",params ="vehicle_brand",produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseUtil searchByVehicleBrand(@RequestParam String vehicle_brand){
        List<VehicleDTO> vehicleDTOArrayList=vehicleService.searchVehicleByName(vehicle_brand);
        return new ResponseUtil("200", vehicle_brand+"Searching", vehicleDTOArrayList);

    }
}