package az.lesson.user.management.service;

import az.lesson.user.management.domain.Role;

public interface RoleService {
    Role findRoleById(Long roleId);

    Role findRoleByType(String role_user);
}
