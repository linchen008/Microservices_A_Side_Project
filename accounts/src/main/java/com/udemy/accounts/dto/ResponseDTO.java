package com.udemy.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:41
 * @Description :
 */
@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response info"
)
public class ResponseDTO {
    @Schema(description = "Status code in the response")
    private String statusCode;
    @Schema(description = "Status message in the response")
    private String statusMsg;
}
