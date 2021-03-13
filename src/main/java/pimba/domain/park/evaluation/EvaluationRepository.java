package pimba.domain.park.evaluation;

import org.springframework.data.repository.CrudRepository;
import pimba.domain.common.Common;

import java.util.Optional;

/**
 * Created by paulo on 14/04/17.
 */
public interface EvaluationRepository extends CrudRepository<Evaluation, Integer> {

    Optional<Evaluation> findByCommon(Common common);
}
