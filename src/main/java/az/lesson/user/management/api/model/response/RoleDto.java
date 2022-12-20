package az.lesson.user.management.api.model.response;

import java.time.LocalDateTime;
import java.util.List;


public record RoleDto(Long id,
                      String type,
                      List<PrivilegeDto> privileges,
                      LocalDateTime createdDate,
                      String createdBy,
                      LocalDateTime lastModifiedDate,
                      String lastModifiedBy) {
}