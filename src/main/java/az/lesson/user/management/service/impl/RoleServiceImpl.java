package az.lesson.user.management.service.impl;

import az.lesson.user.management.domain.Role;
import az.lesson.user.management.exception.ResourceNotFoundException;
import az.lesson.user.management.repository.RoleRepository;
import az.lesson.user.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @PostConstruct
    public void initRoles() {
        Role roleUser = Role.builder()
                .type("ROLE_USER")
                .build();
        Role roleAdmin = Role.builder()
                .type("ROLE_ADMIN")
                .build();
        repository.saveAllAndFlush(List.of(roleUser, roleAdmin));
    }

    @Override
    public Role findRoleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
    }

    @Override
    public Role findRoleByType(String type) {
        return repository.findByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "type", type));
    }
}
