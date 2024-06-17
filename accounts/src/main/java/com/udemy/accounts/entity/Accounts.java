package com.udemy.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 02/06/2024 22:08
 * @Description :
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_number")
    @Id
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "communication_sw")
    private Boolean communicationSw;
}
