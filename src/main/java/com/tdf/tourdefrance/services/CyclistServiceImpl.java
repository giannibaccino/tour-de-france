package com.tdf.tourdefrance.services;

import com.tdf.tourdefrance.dtos.CyclistDto;
import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Cyclist;
import com.tdf.tourdefrance.models.Team;
import com.tdf.tourdefrance.repositories.ICyclistRepository;
import com.tdf.tourdefrance.repositories.ITeamRepository;
import com.tdf.tourdefrance.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CyclistServiceImpl implements ICyclistService{

    @Autowired
    private ICyclistRepository repo;
    @Autowired
    private ITeamRepository teamRepo;

    @Override
    public Mono<CyclistDto> create(Cyclist cyclist) {
        if(cyclist.getCyclistId() <= 999)
            return repo.save(cyclist).thenReturn(AppUtils.cyclistToDto(cyclist));
        return Mono.empty();
    }

    @Override
    public Mono<TeamDto> addCyclistToTeam(Integer cyclistId, String teamId) {
        return teamRepo.findById(teamId)
                .flatMap(team -> repo.findById(cyclistId)
                        .flatMap(cyclist -> {
                            if(team.getCyclists().size() < 8) {
                                team.getCyclists().add(cyclist);
                                repo.save(cyclist);
                            }
                            return teamRepo.save(team).thenReturn(AppUtils.teamToDto(team));
                        }));
    }

    @Override
    public Mono<TeamDto> removeCyclistFromTeam(Integer cyclistId, String teamId) {
        return teamRepo.findById(teamId)
                .flatMap(team -> repo.findById(cyclistId)
                        .flatMap(cyclist -> {
                            team.getCyclists().removeIf(cyclist1 -> cyclist1.getCyclistId().equals(cyclistId));
                            return teamRepo.save(team).thenReturn(AppUtils.teamToDto(team));
                        }));
    }

    @Override
    public Mono<CyclistDto> updateName(Integer cyclistId, String newName) {
        return repo.findById(cyclistId)
                .flatMap(cyclist -> {
                    cyclist.setFullName(newName);
                    return repo.save(cyclist).thenReturn(AppUtils.cyclistToDto(cyclist));
                });
    }

    @Override
    public Flux<CyclistDto> findAll() {
        return AppUtils.cyclistListToDto(repo.findAll());
    }

    @Override
    public Mono<CyclistDto> findByCyclistId(Integer cyclistId) {
        return Mono.just(AppUtils.cyclistToDto(repo.findById(cyclistId).block()));
    }

    @Override
    public Mono<Void> delete(Integer cyclistId) {
        return repo.findById(cyclistId)
                .flatMap(cyclist -> repo.deleteById(cyclist.getCyclistId()));
    }
}
