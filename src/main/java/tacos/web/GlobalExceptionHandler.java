package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tacos.domain.ErrorResponse;
import tacos.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException
            (ResourceNotFoundException exception,
             HttpServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .title(exception.getMessage())
                                                   .details(request.getRequestURI())
                                                   .build();
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException exception) {

        List<ErrorResponse> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(ErrorResponse.builder()
                                    .title(((FieldError) error).getField())
                                    .details(error.getDefaultMessage())
                                    .build());
        });
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
