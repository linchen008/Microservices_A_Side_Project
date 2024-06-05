package com.udemy.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:25
 * @Description :
 */
@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account info"
)
public class CustomerDTO {
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5,max = 30,message = "The length of the customer name should be between 5 and 30")
    @Schema(description = "Name of Customer",example = "Lin Chen")
    private String name;

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(description = "Email address",example = "totoro.douzi@gmail.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Schema(description = "Mobile Number",example = "0892063142")
    private String mobileNumber;

    @Schema(description = "Account Details of the Customer")
    private AccountsDTO accountsDTO;

}
