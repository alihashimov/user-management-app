package az.lesson.user.management.repository;

import az.lesson.user.management.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "user_eg")
    List<User> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = "user_eg")
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}