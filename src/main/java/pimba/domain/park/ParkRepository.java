package pimba.domain.park;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by paulo on 19/03/17.
 */
public interface ParkRepository extends CrudRepository<Park, Integer> {
    Optional<Park> findByName(String name);

}
