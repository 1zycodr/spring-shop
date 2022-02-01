package kz.iitu.itse1909.amirlan.application.user.repository;

import kz.iitu.itse1909.amirlan.application.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
