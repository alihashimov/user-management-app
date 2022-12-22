package az.lesson.user.management.mapper.qualifier;

import az.lesson.user.management.domain.Role;
import az.lesson.user.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRoleQualifier {

    private final RoleService service;

    @Named("mapRoleToUser")
    public Set<Role> mapRoleToUser(Long roleId) {
        return Set.of(service.findRoleById(roleId));
    }


    @Named("mapDefaultRoleToUser")
    public Set<Role> mapDefaultRoleToUser() {
        return Set.of(service.findRoleByType("ROLE_USER"));
    }
}
