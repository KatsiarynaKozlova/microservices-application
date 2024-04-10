package by.me.repository;

import by.me.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredential, Long> {
    Optional<UserCredential> findByEmail(String username);
    boolean existsUserByEmail(String email);
}
