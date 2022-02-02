package kz.iitu.itse1909.amirlan.application.user.repository;

import kz.iitu.itse1909.amirlan.application.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
