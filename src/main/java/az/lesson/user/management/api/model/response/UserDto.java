package az.lesson.user.management.api.model.response;

import az.lesson.user.management.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

public record UserDto(Long id,
                      String username,
                      String fullName,
                      UserStatus status,
                      List<RoleDto> roles,
                      LocalDateTime createdDate,
                      String createdBy,
                      LocalDateTime lastModifiedDate,
                      String lastModifiedBy) {
}