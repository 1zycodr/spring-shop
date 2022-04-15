package kz.iitu.itse1909.amirlan.application.user.repository;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>, PagingAndSortingRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
    AppUser getById(Long id);
}
