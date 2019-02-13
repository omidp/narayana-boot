package com.omid.cloud.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omid.cloud.client.model.PaymentEntity;

public interface PaymentDao extends JpaRepository<PaymentEntity, Long>
{

}
