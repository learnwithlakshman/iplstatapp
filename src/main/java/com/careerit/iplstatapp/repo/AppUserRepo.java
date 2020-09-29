package com.careerit.iplstatapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.careerit.iplstatapp.domain.AppUser;

public interface AppUserRepo extends MongoRepository<AppUser,String>{

}
