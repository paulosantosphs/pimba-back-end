package pimba.login.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pimba.login.model.passport.Passport;

import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Long> {

    @Query("SELECT p FROM Passport p WHERE p.user.id = ?1 AND TYPE(p) = ?2")
    public Optional<Passport> findByUserIdAndType(Long userId, Class<? extends Passport> type);
}
