package com.tdf.tourdefrance.controllers;

import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Cyclist;
import com.tdf.tourdefrance.models.Team;
import com.tdf.tourdefrance.services.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private ITeamService service;

    @PostMapping("/create")
    public Mono<TeamDto> create(@RequestBody Team team) {
        return service.create(team);
    }

    @PutMapping("/changeName/{teamId}")
    public Mono<TeamDto> updateName(@PathVariable("teamId") String teamId, @RequestBody String newName) {
        return service.updateName(teamId, newName);
    }

    @GetMapping()
    public Flux<TeamDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/getTeam/{teamId}")
    public Mono<TeamDto> findById(@PathVariable("teamId") String teamId) {
        return service.findByTeamId(teamId);
    }

    @GetMapping("/getTeamsByCountry/{country}")
    public Flux<TeamDto> findByCountry(@PathVariable("country") String country) {
        return service.findAll().filter(team -> team.getCountry().toLowerCase().equals(country.toLowerCase()));
    }

    @DeleteMapping("/deleteTeam/{teamId}")
    public Mono<Void> deleteTeam(@PathVariable("teamId") String itemId) {
        return service.delete(itemId);
    }
}