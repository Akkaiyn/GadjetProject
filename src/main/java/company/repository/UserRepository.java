package company.repository;

import company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
}
