package az.lesson.user.management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
public class ResourceConflictException extends RuntimeException {

    private final HttpStatus status;

    public ResourceConflictException(String resourceName, String fieldName, Object value) {
        super(format("%s already exists by %s : %s", resourceName, fieldName, value));
        status = CONFLICT;
    }
}
