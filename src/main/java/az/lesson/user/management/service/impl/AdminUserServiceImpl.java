package az.lesson.user.management.service.impl;

import az.lesson.user.management.api.model.request.AdminUserRegister;
import az.lesson.user.management.api.model.response.RestPageResponse;
import az.lesson.user.management.api.model.response.UserDto;
import az.lesson.user.management.domain.User;
import az.lesson.user.management.enums.UserStatus;
import az.lesson.user.management.exception.ResourceConflictException;
import az.lesson.user.management.exception.ResourceNotFoundException;
import az.lesson.user.management.mapper.UserMapper;
import az.lesson.user.management.repository.UserRepository;
import az.lesson.user.management.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static az.lesson.user.management.enums.UserStatus.BLOCKED;
import static az.lesson.user.management.enums.UserStatus.DISABLE;
import static az.lesson.user.management.enums.UserStatus.ENABLE;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository repository;
    public final UserMapper mapper;

    @Override
    public UserDto register(AdminUserRegister adminUserRegister) {
        checkUsername(adminUserRegister.getUsername());
        var user = repository.saveAndFlush(mapper.toEntity(adminUserRegister));
        return mapper.toUserDto(user);
    }

    @Override
    public UserDto enableUser(Long id) {
        return changeUserStatus(id, ENABLE);
    }

    @Override
    public UserDto disableUser(Long id) {
        return changeUserStatus(id, DISABLE);
    }

    @Override
    public UserDto blockedUser(Long id) {
        return changeUserStatus(id, BLOCKED);
    }

    @Override
    public void deleteUserById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public RestPageResponse<UserDto> getAllUsers(Integer pageIndex, Integer pageSize) {
        var userPage = repository.findAll(PageRequest.of(pageIndex, pageSize));
        return mapper.toUserPage(userPage);
    }

    private void checkUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new ResourceConflictException("User", "username", username);
        }
    }

    public UserDto changeUserStatus(Long id, UserStatus status) {
        var user = findById(id);
        user.setStatus(status);
        return mapper.toUserDto(repository.saveAndFlush(user));
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}