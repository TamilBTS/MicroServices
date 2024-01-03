package com.spring.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDto {

    @Schema(name = "Name of the customer", example = "Tamilarasi")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer should be 5 and 30")
    private String name;

    @Schema(name = "Email address of the customer", example = "tamil@gmail.com")
    @Email(message = "Email address should be a valid value")
    @NotEmpty(message = "Email cannot be null or empty")
    private String email;

    @Schema(name = "Mobile number of the customer", example = "1234567890")
    @Pattern(regexp = "[0-9]{10}",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Valid
    @Schema(name = "Account Details of the customer")
    private AccountsDto accountsDto;

}
