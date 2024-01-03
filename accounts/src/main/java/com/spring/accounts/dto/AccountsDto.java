package com.spring.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Schema(name = "Account", description = "Schema to hold Account information")
public class AccountsDto {

    @Schema(description = "Account Number of the customer", example = "1209871611")
    @NotNull(message = "Account Number cannot be null or empty")
    @Digits(integer = 10, fraction = 0, message = "Account Number must be 10 digits")
    private Long accountNumber;
    @Schema(description = "Account Type of the customer", example = "Savings")
    @NotEmpty(message = "Account Type cannot be null or empty")
    private String accountType;
    @Schema(description = "Branch Address of the customer", example = "104, KTC Nagar")
    @NotEmpty(message = "Branch Address cannot be null or empty")
    private String branchAddress;

}
