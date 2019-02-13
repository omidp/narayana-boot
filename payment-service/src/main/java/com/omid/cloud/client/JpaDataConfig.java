package com.omid.cloud.client;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Omid Pourhadi
 *
 */
@Configuration
@EntityScan({ "com.omid.cloud.client.model" })
@EnableJpaRepositories({ "com.omid.cloud.client.dao" })
@EnableJpaAuditing
public class JpaDataConfig
{


}
