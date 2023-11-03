package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.repo.VehicleRepo;
import lk.ijse.vehicleservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void addVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getId())){
            throw new RuntimeException(vehicleDTO.getId()+"Already Exists");
        }

        Vehicle vehicle=modelMapper.map(vehicleDTO,Vehicle.class);
//        vehicle.setVehicle_brand(vehicleDTO.getVehicle_brand());
//        vehicle.setVehicle_img(vehicleDTO.getVehicle_img());
//        vehicle.setVehicle_category(vehicleDTO.getVehicle_category());
//        vehicle.setFuel_type(vehicleDTO.getFuel_type());
//        vehicle.getDriver_contact(vehicleDTO.getDriver_contact());

        vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicele(long id) {
    if (!vehicleRepo.existsById(id)){
    throw new RuntimeException("Vehicle"+id+"Not Available Deleted");
        }
        vehicleRepo.deleteById(id);
    }

    @Override
    public void updateVehicle(Vehicle vehicleDTO) {
        Optional<Vehicle>Byid=vehicleRepo.findById(vehicleDTO.getId());
        if (Byid.isEmpty()){
            throw new RuntimeException("Id doesent Exists");
        }
        vehicleRepo.save(modelMapper.map(vehicleDTO,Vehicle.class));
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicles() {
        return modelMapper.map(vehicleRepo.findAll(), new TypeToken<ArrayList<VehicleDTO>>() {}.getType());
    }

    @Override
    public ArrayList<VehicleDTO> searchVehicleByName(String vehicle_brand) {

        return modelMapper.map(vehicleRepo.findAllBy(vehicle_brand), new TypeToken<ArrayList<VehicleDTO>>() {}.getType());
    }

    @Override
    public Vehicle findById(long id) {
        if (!vehicleRepo.existsById(id)){
            throw new RuntimeException("Vehicle"+id+"Not Available");
        }
        Optional<Vehicle>byId=vehicleRepo.findById(id);
        return byId.get();
    }
}
