package az.lesson.user.management.api.model.response;

import az.lesson.user.management.enums.UserStatus;

import java.time.LocalDateTime;

public record UserDto(Long id,
                      String username,
                      String fullName,
                      UserStatus status,
                      LocalDateTime createdDate,
                      String createdBy,
                      LocalDateTime lastModifiedDate,
                      String lastModifiedBy) {
}