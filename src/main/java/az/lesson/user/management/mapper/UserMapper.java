package az.lesson.user.management.mapper;

import az.lesson.user.management.api.model.request.AdminUserRegister;
import az.lesson.user.management.api.model.request.PublicUserRegister;
import az.lesson.user.management.api.model.response.RestPageResponse;
import az.lesson.user.management.api.model.response.UserDto;
import az.lesson.user.management.domain.User;
import az.lesson.user.management.mapper.qualifier.UserRoleQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = UserRoleQualifier.class)
public interface UserMapper {

    @Mapping(target = "roles", qualifiedByName = "mapRoleToUser")
    User toEntity(AdminUserRegister adminUserRegister);

    @Mapping(target = "roles", qualifiedByName = "mapDefaultRoleToUser")
    User toEntity(PublicUserRegister publicUserRegister);

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> users);

    default RestPageResponse<UserDto> toUserPage(Page<User> userPage) {
        var users = toUserDto(userPage.getContent());
        return new RestPageResponse<>(
                users,
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.getNumber(),
                userPage.getNumberOfElements(),
                userPage.hasNext(),
                userPage.hasPrevious());
    }

}
