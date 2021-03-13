package pimba.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.login.spring.service.SecurityService;

/**
 * Created by paulo on 26/04/17.
 */
@Service
public class CommonService {

    @Autowired
    CommonRepository commonRepository;

    @Autowired
    private SecurityService securityService;


    public Common editName(String name) {
        Common common = securityService.getCurrentUser().get().getCommon();
        common.setName(name);
        commonRepository.save(common);
        return common;
    }

    public Common editPhone(String phone) {
        Common common = securityService.getCurrentUser().get().getCommon();
        common.setPhone(phone);
        commonRepository.save(common);
        return common;
    }

    public Common editGender(Gender gender) {
        Common common = securityService.getCurrentUser().get().getCommon();
        common.setGender(gender);
        commonRepository.save(common);
        return common;
    }

    public Common editPhoto(String photo) {
        Common common = securityService.getCurrentUser().get().getCommon();
        common.setPhoto(photo);
        commonRepository.save(common);
        return common;

    }

    public Common editCity(String city) {
        Common common = securityService.getCurrentUser().get().getCommon();
        common.setCity(city);
        commonRepository.save(common);
        return common;
    }

}
