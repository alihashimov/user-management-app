package az.lesson.user.management.repository;

import az.lesson.user.management.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByType(String roleType);
}