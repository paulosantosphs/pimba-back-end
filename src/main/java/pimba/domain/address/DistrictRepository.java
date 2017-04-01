package pimba.domain.address;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by joao on 01/12/16.
 */
public interface DistrictRepository extends CrudRepository<District, Integer> {

    Optional<District> findByName(String name);
}
