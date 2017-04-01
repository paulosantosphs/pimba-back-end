package pimba.login.repository;

import org.springframework.data.repository.CrudRepository;
import pimba.login.model.user.User;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
