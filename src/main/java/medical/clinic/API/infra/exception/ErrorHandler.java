package medical.clinic.API.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(errorDataValidation::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity businessRuleErrors(ValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record errorDataValidation(String field, String message){
        public errorDataValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
