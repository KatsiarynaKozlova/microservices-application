package by.me.repository;

import by.me.dto.UserDTO;
import by.me.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredential, Long> {
    boolean existsByEmail(String email);
    UserDTO findByEmail(String username);
}
