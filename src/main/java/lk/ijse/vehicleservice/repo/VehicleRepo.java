package lk.ijse.vehicleservice.repo;

import lk.ijse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {


    void deleteById(long id);

    @Query(value = "SELECT v FROM Vehicle v WHERE v.vehicle_brand = :vehicle_brand")
   ArrayList<Vehicle> findAllBy(String vehicle_brand);


}
