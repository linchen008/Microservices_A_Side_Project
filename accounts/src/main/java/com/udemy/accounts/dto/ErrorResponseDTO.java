package com.udemy.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 03/06/2024 19:28
 * @Description :
 */
@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold Error response info"
)
public class ErrorResponseDTO {
    @Schema(description = "API path invoked by Client")
    private String apiPath;
    @Schema(description = "Error Code representing the error happend")
    private HttpStatus errorCode;
    @Schema(description = "Error message representing the error happend")
    private String errorMessage;
    @Schema(description = "Time representing when the error happend")
    private LocalDateTime errorTime;
}
