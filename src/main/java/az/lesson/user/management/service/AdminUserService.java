package az.lesson.user.management.service;

import az.lesson.user.management.api.model.request.AdminUserRegister;
import az.lesson.user.management.api.model.response.RestPageResponse;
import az.lesson.user.management.api.model.response.UserDto;

public interface AdminUserService {

    UserDto register(AdminUserRegister adminUserRegister);

    UserDto enableUser(Long id);

    UserDto disableUser(Long id);

    UserDto blockedUser(Long id);

    void deleteUserById(Long id);

    RestPageResponse<UserDto> getAllUsers(Integer pageIndex, Integer pageSize);

}
