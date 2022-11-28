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
        if(team.getTeamId().matches("^[a-zA-Z0-9]*$"))
            return service.create(team);
        return Mono.error(new Error("Invalid id (alphanumeric)"));
    }

    @PutMapping("/changeName/{teamId}")
    public Mono<TeamDto> updateName(@PathVariable("teamId") String teamId, @RequestBody String newName) {
        if(teamId.matches("^[a-zA-Z0-9]*$"))
            return service.updateName(teamId, newName);
        return Mono.error(new Error("Team Id does not exist (alphanumeric)"));
    }

    @GetMapping()
    public Flux<TeamDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/getTeam/{teamId}")
    public Mono<TeamDto> findById(@PathVariable("teamId") String teamId) {
        if(teamId.matches("^[a-zA-Z0-9]*$"))
            return service.findByTeamId(teamId);
        return Mono.error(new Error("Team Id does not exist (alphanumeric)"));
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