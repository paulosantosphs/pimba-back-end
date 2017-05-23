package pimba.domain.park.rate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pimba.domain.park.Park;

import java.util.Optional;

/**
 * Created by paulo on 14/04/17.
 */
@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {

    Optional<Rate> findByParkAndRatePeriod(Park park, RatePeriod period);
}
