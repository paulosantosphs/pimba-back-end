package pimba.domain.evaluation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pimba.domain.address.Address;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.domain.park.evaluation.Evaluation;
import pimba.domain.park.evaluation.EvaluationRepository;
import pimba.domain.park.evaluation.EvaluationService;
import pimba.exceptions.InvalidEvaluationException;
import pimba.login.model.user.User;
import pimba.login.spring.service.impl.SecurityServiceImpl;

import java.util.*;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 16/05/17.
 */
public class EvaluationServiceTest {
    @InjectMocks
    EvaluationService service;

    @Mock
    private ParkRepository parkRepository;

    @Mock
    private EvaluationRepository evaluationRepository;

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
        when(parkRepository.save(park)).thenReturn(park);

        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        User user = new User("paulosantosphs@hotmail.com", common);
        when(security.getCurrentUser()).thenReturn(Optional.of(user));

        Evaluation evaluation = new Evaluation(4, common, park);
        when(evaluationRepository.findByCommon(common)).thenReturn(Optional.of(evaluation));

        Evaluation evaluation1 = new Evaluation(5, common, park);
        Evaluation evaluation2 = new Evaluation(5, common, park);
        List<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(evaluation1);
        evaluations.add(evaluation2);
        Set<Evaluation> evaluationSet = new HashSet<>(evaluations);

        park.setEvaluations(evaluationSet);
        Assert.assertEquals(evaluation, service.save(0, 4));

        int star = service.save(0, 2).getStarNumber();
        Assert.assertEquals(2, star);

        try {
            service.save(0, 7);
        } catch (InvalidEvaluationException e) {
            Assert.assertEquals("Stars must be between 0 and 5", e.getMessage());
        }
    }
}
