package pimba.domain.park.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.exceptions.InvalidParkException;
import pimba.login.spring.service.SecurityService;

/**
 * Created by paulo on 11/05/17.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private SecurityService securityService;

    public Comment register(Integer parkId, String comment) {
        Park park = parkRepository.findById(parkId).orElseThrow(() -> new InvalidParkException("Parking not found"));
        Common common = securityService.getCurrentUser().get().getCommon();
        Comment comment1 = new Comment(comment, common, park);
        return commentRepository.save(comment1);
    }
}
