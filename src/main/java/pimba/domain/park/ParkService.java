package pimba.domain.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.exceptions.InvalidParkException;

/**
 * Created by paulo on 02/04/17.
 */
@Service
public class ParkService {
    @Autowired
    private ParkRepository parkRepository;


    public Park getParkByName(String name) {
        Park park = parkRepository.findByName(name).orElseThrow(() -> new InvalidParkException("Parking not found"));
        return park;
    }
}
