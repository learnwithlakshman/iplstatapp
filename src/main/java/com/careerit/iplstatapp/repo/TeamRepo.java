package com.careerit.iplstatapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.careerit.iplstatapp.domain.Team;

public interface TeamRepo extends MongoRepository<Team, String> {

}
