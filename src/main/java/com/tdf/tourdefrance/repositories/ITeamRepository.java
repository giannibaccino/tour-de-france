package com.tdf.tourdefrance.repositories;

import com.tdf.tourdefrance.models.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository extends ReactiveMongoRepository<Team, String>{
}
