package az.lesson.user.management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public ResourceNotFoundException(String resourceName, String fieldName, Object value) {
        super(format("%s was not found by %s : %s", resourceName, fieldName, value));
        status = NOT_FOUND;
    }

}
