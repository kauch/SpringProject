package com.netcracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories // ("com.netcracker.repositories")
public class RepositoryConfiguration {

}
