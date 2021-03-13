package pimba.domain.common.historic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.exceptions.InvalidHistoricException;
import pimba.exceptions.InvalidParkException;
import pimba.login.spring.service.SecurityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 11/05/17.
 */
@Service
public class HistoricService {

    @Autowired
    private HistoricRepository historicRepository;

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private SecurityService securityService;

    public Historic register(Double payment, Integer parkId, Boolean botton) {
        Park park = parkRepository.findById(parkId).orElseThrow(() -> new InvalidParkException("Parking not found"));
        if (payment != null && park.getCustomer() == null) {
            throw new InvalidHistoricException("Payment must be null if the parking isn't PIMBA");
        }
        Common common = securityService.getCurrentUser().get().getCommon();
        Historic historic = new Historic(payment, botton, park, common);
        historicRepository.save(historic);
        return historic;
    }

    public List<Historic> getHistoric() {
        Common common = securityService.getCurrentUser().get().getCommon();
        if (common.getHistoric().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<Historic> historic = new ArrayList<>(common.getHistoric());
            return historic;
        }
    }
}
