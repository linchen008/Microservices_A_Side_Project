package com.udemy.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 15:27
 * @Description :
 */
@Schema(
        name = "Loans",
        description = "Schema to hold Loan Info"
)
@Data
public class LoansDTO {

//  `mobile_number` varchar(15) NOT NULL,
//  `loan_number` varchar(100) NOT NULL,
//  `loan_type` varchar(100) NOT NULL,
//  `total_loan` int NOT NULL,
//   `amount_paid` int NOT NULL,
//   `outstanding_amount` int NOT NULL

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Schema(description = "Mobilde number of Customer",example = "0892063143")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @Schema(description = "Loan Number of the customer", example = "1234562783424")
    private String loanNumber;

    @NotEmpty(message = "LoanType can not be a null or empty")
    @Schema(description = "Type of the loan",example = "Home Loan")
    private String loanType;

    @Positive(message = "TotalLoan amount should be greater than 0")
    @Schema(description = "TotalLoan amount",example = "1000000")
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than 0")
    @Schema(description = "Total outstanding amount against a loan",example = "909090")
    private int amountPaid;

    private int  outstandingAmount;
}
