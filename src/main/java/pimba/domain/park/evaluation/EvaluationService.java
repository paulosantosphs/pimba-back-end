package pimba.domain.park.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.exceptions.InvalidEvaluationException;
import pimba.exceptions.InvalidParkException;
import pimba.login.spring.service.SecurityService;

/**
 * Created by paulo on 13/05/17.
 */
@Service
public class EvaluationService {
    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EvaluationRepository evaluationRepository;

    public Evaluation save(Integer parkId, Integer stars) {
        if (stars > 5 || stars < 0) {
            throw new InvalidEvaluationException("Stars must be between 0 and 5");
        } else {
            Park park = parkRepository.findById(parkId).orElseThrow(() -> new InvalidParkException("Parking not found"));
            Common common = securityService.getCurrentUser().get().getCommon();
            Evaluation evaluation = evaluationRepository.findByCommon(common).orElseGet(() -> {
                Evaluation newEvaluation = new Evaluation(stars, common, park);
                return evaluationRepository.save(newEvaluation);
            });
            if (evaluation.getStarNumber() != stars) {
                evaluation.setStarNumber(stars);
                evaluationRepository.save(evaluation);
            }
            park.setEvaluation();
            parkRepository.save(park);
            return evaluation;
        }
    }
}
