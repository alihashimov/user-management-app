package az.lesson.user.management.mapper.qualifier;

import az.lesson.user.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoleQualifier {

    private final RoleService service;

//    @Named("mapRoleToUser")
}
