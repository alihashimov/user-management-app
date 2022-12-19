package az.lesson.user.management.api.model.response;

import java.time.LocalDateTime;


public record PrivilegeDto(Long id,
                           String type,
                           LocalDateTime createdDate,
                           String createdBy,
                           LocalDateTime lastModifiedDate,
                           String lastModifiedBy) {
}