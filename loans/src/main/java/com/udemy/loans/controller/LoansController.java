package com.udemy.loans.controller;

import com.udemy.loans.constants.LoansConstants;
import com.udemy.loans.dto.LoansContactInfoDTO;
import com.udemy.loans.dto.LoansDTO;
import com.udemy.loans.dto.ResponseDTO;
import com.udemy.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 15:15
 * @Description :
 */
@Tag(
        name = "CRUD REST APIs for Loans",
        description = "CRUD REST APIs in Lin's Bank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class LoansController {

    private final ILoansService iLoansService;

    public LoansController(ILoansService iLoansService) {
        this.iLoansService = iLoansService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDTO loansContactInfoDTO;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside Lin's Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                  String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fecth Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "",
                    description = ""
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits")
                                                     String mobileNumber) {
        LoansDTO loansDTO = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDTO);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API To update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoansDTO loansDTO) {
        boolean isUpdated = iLoansService.updateLoan(loansDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete loan Details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDTO);
    }
}