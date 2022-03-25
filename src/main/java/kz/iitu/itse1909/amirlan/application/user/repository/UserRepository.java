package kz.iitu.itse1909.amirlan.application.user.repository;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
}
