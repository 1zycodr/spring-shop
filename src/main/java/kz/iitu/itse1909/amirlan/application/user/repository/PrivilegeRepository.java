package kz.iitu.itse1909.amirlan.application.user.repository;

import kz.iitu.itse1909.amirlan.application.user.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findPrivilegeByName(String name);
}
