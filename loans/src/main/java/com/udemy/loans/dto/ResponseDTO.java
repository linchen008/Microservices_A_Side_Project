package com.udemy.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 15:27
 * @Description :
 */
@Schema(
        name = "Response",
        description = "Schema to hold successful response info"
)
@Data
@AllArgsConstructor
public class ResponseDTO {
    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response"
    )
    private String statusMsg;
}
