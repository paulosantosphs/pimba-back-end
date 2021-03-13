package pimba.domain.historic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pimba.domain.address.Address;
import pimba.domain.common.Common;
import pimba.domain.common.historic.Historic;
import pimba.domain.common.historic.HistoricRepository;
import pimba.domain.common.historic.HistoricService;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.exceptions.InvalidHistoricException;
import pimba.login.model.user.User;
import pimba.login.spring.service.impl.SecurityServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 15/05/17.
 */
public class HistoricServiceTest {
    @InjectMocks
    HistoricService service;

    @Mock
    HistoricRepository historicRepository;

    @Mock
    ParkRepository parkRepository;

    @Mock
    SecurityServiceImpl security;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        Address address = new Address(-20.387719, -43.506921);
        Park park = new Park(address, "Park", "park");
        when(parkRepository.findById(0)).thenReturn(Optional.of(park));

        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        User user = new User("paulosantosphs@hotmail.com", common);
        when(security.getCurrentUser()).thenReturn(Optional.of(user));

        Historic historic = new Historic(null, true, park, common);
        when(historicRepository.save(historic)).thenReturn(historic);
        Assert.assertEquals(historic.getCommon(), service.register(null, 0, true).getCommon());
        Assert.assertEquals(historic.getBotton(), service.register(null, 0, true).getBotton());
        Assert.assertEquals(historic.getPark(), service.register(null, 0, true).getPark());
        Assert.assertEquals(historic.getPayment(), service.register(null, 0, true).getPayment());

        Historic historic1 = new Historic(50.5, true, park, common);
        when(historicRepository.save(historic1)).thenReturn(historic1);
        try {
            service.register(50.5, 0, true);
        } catch (InvalidHistoricException e) {
            Assert.assertEquals("Payment must be null if the parking isn't PIMBA", e.getMessage());
        }

    }

}
