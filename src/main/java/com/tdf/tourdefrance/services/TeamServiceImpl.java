package com.tdf.tourdefrance.services;

import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Cyclist;
import com.tdf.tourdefrance.models.Team;
import com.tdf.tourdefrance.repositories.ITeamRepository;
import com.tdf.tourdefrance.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeamServiceImpl implements ITeamService{

    @Autowired
    private ITeamRepository repo;

    @Override
    public Mono<TeamDto> create(Team team) {
        if(team.getTeamId().length() <= 3)
            return repo.save(team).thenReturn(AppUtils.teamToDto(team));
        return Mono.empty();
    }

    @Override
    public Mono<TeamDto> updateName(String teamId, String newName) {
        return repo.findById(teamId)
                .flatMap(team -> {
                    team.setTeamName(newName);
                    return repo.save(team).thenReturn(AppUtils.teamToDto(team));
                });
    }

    @Override
    public Flux<TeamDto> findAll() {

        return AppUtils.teamListToDto(repo.findAll());
    }

    @Override
    public Mono<TeamDto> findByTeamId(String teamId) {
        return Mono.just(AppUtils.teamToDto(repo.findById(teamId).block()));
    }
    
    @Override
    public Mono<Void> delete(String teamId) {
        return repo.findById(teamId)
                .flatMap(team -> repo.deleteById(team.getTeamId()));
    }
}
