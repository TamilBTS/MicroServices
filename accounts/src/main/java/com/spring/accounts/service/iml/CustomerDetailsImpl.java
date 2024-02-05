package com.spring.accounts.service.iml;

import com.spring.accounts.dto.AccountsDto;
import com.spring.accounts.dto.CardsDto;
import com.spring.accounts.dto.CustomerDetailsDto;
import com.spring.accounts.dto.LoansDto;
import com.spring.accounts.entity.Accounts;
import com.spring.accounts.entity.Customer;
import com.spring.accounts.execption.ResourceNotFountException;
import com.spring.accounts.mapper.AccountsMapper;
import com.spring.accounts.mapper.CustomerMapper;
import com.spring.accounts.repository.AccountRepository;
import com.spring.accounts.repository.CustomerRepository;
import com.spring.accounts.service.ICustomerService;
import com.spring.accounts.service.client.CardsFeignClient;
import com.spring.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFountException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFountException("Accounts", "customerId", String.valueOf(customer.getCustomerId())));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> response = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(response.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
