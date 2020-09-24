package com.careerit.iplstatapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.careerit.iplstatapp.domain.Player;

public interface PlayerRepo extends MongoRepository<Player, String> {

}
