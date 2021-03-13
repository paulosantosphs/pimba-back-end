package pimba.login.repository;

import org.springframework.data.repository.CrudRepository;
import pimba.login.model.user.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
