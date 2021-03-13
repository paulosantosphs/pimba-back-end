package pimba.domain.vehicle;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by paulo on 19/03/17.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Optional<Vehicle> findById(Integer id);
}
