package com.tdf.tourdefrance.services;

import com.tdf.tourdefrance.dtos.CyclistDto;
import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Cyclist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICyclistService {
    Mono<CyclistDto> create(Cyclist cyclist);
    Mono<TeamDto> addCyclistToTeam(Integer cyclistId, String teamId);
    Mono<TeamDto> removeCyclistFromTeam(Integer cyclistId, String teamId);
    Mono<CyclistDto> updateName(Integer cyclistId, String newName);
    Flux<CyclistDto> findAll();
    Mono<CyclistDto> findByCyclistId(Integer cyclistId);
    Mono<Void> delete(Integer cyclistId);
}
