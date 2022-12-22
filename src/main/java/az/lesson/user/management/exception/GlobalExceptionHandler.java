package az.lesson.user.management.exception;

import az.lesson.user.management.api.model.response.FieldError;
import az.lesson.user.management.api.model.response.RestError;
import az.lesson.user.management.api.model.response.RestErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.time.LocalDateTime.now;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper mapper;

    @SneakyThrows
    @ExceptionHandler(ResourceConflictException.class)
    protected ResponseEntity<RestErrorResponse<RestError>> handleResourceConflictException(
            ResourceConflictException ex) {
        var error = RestError.builder()
                .reason(ex.getStatus().getReasonPhrase())
                .code(ex.getStatus().value())
                .timestamp(now())
                .message(ex.getMessage())
                .build();
        log.error("ResourceConflictException: {}", mapper.writeValueAsString(error));
        return new ResponseEntity<>(new RestErrorResponse<>(error), ex.getStatus());
    }

    @SneakyThrows
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<RestErrorResponse<RestError>> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        var error = RestError.builder()
                .reason(ex.getStatus().getReasonPhrase())
                .code(ex.getStatus().value())
                .timestamp(now())
                .message(ex.getMessage())
                .build();
        log.error("ResourceNotFoundException: {}", mapper.writeValueAsString(error));
        return new ResponseEntity<>(new RestErrorResponse<>(error), ex.getStatus());
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        var fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .toList();
        var error = RestError.builder()
                .reason(status.getReasonPhrase())
                .code(status.value())
                .timestamp(now())
                .message(ex.getMessage())
                .fieldErrors(fieldErrors)
                .build();
        log.error("MethodArgumentNotValidException: {}", mapper.writeValueAsString(error));
        return new ResponseEntity<>(new RestErrorResponse<>(error), status);
    }
}
