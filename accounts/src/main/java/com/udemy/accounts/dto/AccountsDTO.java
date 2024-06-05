package com.udemy.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:04
 * @Description :
 */
@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account Info"
)
public class AccountsDTO {
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp = "^$|[0-9]{10}",message = "AccountNumber must be 10 digits.")
    @Schema(
            description = "Account Number",example = "1234567890"
    )
    private Long accountNumber;

    @NotEmpty(message = "AccountType can not be  a null or empty")
    @Schema(
            description = "Account type", example = "Saving"
    )
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be a null or empty")
    @Schema(
            description = "Branch Address", example = "15 Dublin"
    )
    private String branchAddress;
}
