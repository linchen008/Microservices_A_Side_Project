package com.udemy.cards.exception;

import com.udemy.cards.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 22:04
 * @Description :
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDTO);
    }

    /**
     * Customize the handling of {@link MethodArgumentNotValidException}.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     *
     * @param ex      the exception to handle
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} for the response to use, possibly
     * {@code null} when the response is already committed
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        for (ObjectError objectError : validationErrorList) {
            String fieldName = ((FieldError)objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        }
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCardAlreadyExistsException(
            CardAlreadyExistsException ex, WebRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
