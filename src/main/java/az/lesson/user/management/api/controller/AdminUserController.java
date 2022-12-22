package az.lesson.user.management.api.controller;

import az.lesson.user.management.api.model.request.AdminUserRegister;
import az.lesson.user.management.api.model.response.RestPageResponse;
import az.lesson.user.management.api.model.response.UserDto;
import az.lesson.user.management.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    private final AdminUserService service;

    @PostMapping
    public ResponseEntity<UserDto> register(
            @Valid @NotNull @RequestBody AdminUserRegister adminUserRegister) {
        log.info("Request for user registration by admin. Request body: {}", adminUserRegister);
        var userDto = service.register(adminUserRegister);
        return new ResponseEntity<>(userDto, CREATED);
    }

    @GetMapping
    public ResponseEntity<RestPageResponse<UserDto>> getAllUsers(
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        log.info("Request for fetch all users with pagination by admin. " +
                "Request pageIndex: {}, pageSize: {}", pageIndex, pageSize);
        var userPage = service.getAllUsers(pageIndex, pageSize);
        return ResponseEntity.ok(userPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @NotNull Long id) {
        log.info("Request for fetch user with id: {} by admin", id);
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<UserDto> enableUserById(@PathVariable @NotNull Long id) {
        log.info("Request for enable user with id: {} by admin", id);
        var userDto = service.enableUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<UserDto> disableUserById(@PathVariable @NotNull Long id) {
        log.info("Request for disable user with id: {} by admin", id);
        var userDto = service.disableUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}/blocked")
    public ResponseEntity<UserDto> blockedUserById(@PathVariable @NotNull Long id) {
        log.info("Request for blocked user with id: {} by admin", id);
        var userDto = service.blockedUser(id);
        return ResponseEntity.ok(userDto);
    }

}
