package com.spring.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Lombok;

@Data
@Entity
@Table(name = "account")
public class Accounts extends BaseEntity{

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;

}
