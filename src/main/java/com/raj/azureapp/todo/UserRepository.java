package com.raj.azureapp.todo;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CosmosRepository<User, String> {

}