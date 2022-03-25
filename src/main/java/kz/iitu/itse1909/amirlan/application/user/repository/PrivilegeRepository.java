package kz.iitu.itse1909.amirlan.application.user.repository;

import kz.iitu.itse1909.amirlan.application.user.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    @Transactional(propagation = Propagation.REQUIRED)
    Privilege findPrivilegeByName(String name);
}
