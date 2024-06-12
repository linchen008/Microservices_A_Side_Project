package com.udemy.accounts.controller;

import com.udemy.accounts.dto.CustomerDetailsDTO;
import com.udemy.accounts.dto.ErrorResponseDTO;
import com.udemy.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 10/06/2024 21:57
 * @Description :
 */
@Tag(
        name = "Rest API for Customer in Lin's Bank",
        description = "REST APIs in Lin's Bank to Fetch customers details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class CustomerController {

    private final ICustomerService iCustomerService;

    //DI with Constructor
    public CustomerController(ICustomerService customerService) {
        this.iCustomerService = customerService;
    }


    @Operation(
            summary = "Fetch Customer Details Rest API",
            description = "Rest Api to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    }
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestHeader("X-Correlation-Id")
                                                                   String correlationId,
                                                                   @Pattern(regexp = "(^$|[0-9]{10})",
                                                                           message = "mobile number should be 10 digits")
                                                                   String mobileNumber) {
        log.info("X-Correlation-Id found: {}", correlationId);
        CustomerDetailsDTO customerDetailsDTO = iCustomerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDTO);
    }

}
