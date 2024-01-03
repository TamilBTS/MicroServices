package com.spring.accounts.service.iml;

import com.spring.accounts.constants.AccountConstants;
import com.spring.accounts.dto.AccountsDto;
import com.spring.accounts.dto.CustomerDto;
import com.spring.accounts.entity.Accounts;
import com.spring.accounts.entity.Customer;
import com.spring.accounts.execption.CustomerExistsException;
import com.spring.accounts.execption.ResourceNotFountException;
import com.spring.accounts.mapper.AccountsMapper;
import com.spring.accounts.mapper.CustomerMapper;
import com.spring.accounts.repository.AccountRepository;
import com.spring.accounts.repository.CustomerRepository;
import com.spring.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.maptoCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent())
            throw new CustomerExistsException("Customer already exists with this mobile number " + customerDto.getMobileNumber());
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFountException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFountException("Accounts", "customerId", String.valueOf(customer.getCustomerId())));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public Boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFountException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFountException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.maptoCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public Boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFountException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long accountNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(accountNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        return accounts;
    }
}
