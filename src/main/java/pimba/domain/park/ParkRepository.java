package pimba.domain.park;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by paulo on 19/03/17.
 */
@Repository
public interface ParkRepository extends CrudRepository<Park, Integer> {

    Optional<Park> findByName(String name);

    @Query("SELECT p FROM Park p JOIN FETCH p.address WHERE (p.address.latitude BETWEEN (?1-?3) AND (?1+?3))" +
            "AND (p.address.longitude BETWEEN (?2-?4) AND (?2+?4))")
    List<Park> getListParkByLocation(Double latitude, Double longitude, Double latitudeRadius, Double longitudeRadius);


}
