package com.tdf.tourdefrance.services;

import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Team;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITeamService {
    Mono<TeamDto> create(Team team);
    Mono<TeamDto> updateName(String teamId, String newName);
    Flux<TeamDto> findAll();
    Mono<TeamDto> findByTeamId(String teamId);
    Mono<Void> delete(String teamId);
}
