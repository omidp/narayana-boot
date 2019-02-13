package com.omid.cloud.client.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable
{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "amt")
    private Long amount;

    @Column(name = "acc_id")
    private Long accountId;

    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getAmount()
    {
        return amount;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public PaymentEntity()
    {
    }

    public PaymentEntity(Long amount, Long accountId)
    {
        this.amount = amount;
        this.accountId = accountId;
    }
    
    

}
