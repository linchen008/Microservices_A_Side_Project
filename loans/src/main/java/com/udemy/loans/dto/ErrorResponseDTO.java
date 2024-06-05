package com.udemy.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 15:28
 * @Description :
 */
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response info"
)
@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    @Schema(description = "API Path invoked by client")
    private String apiPath;

    @Schema(description = "Error Code representing the error code")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error message")
    private String errorMessage;

    @Schema(description = "Time representing when the error happend")
    private LocalDateTime errorTime;
}
