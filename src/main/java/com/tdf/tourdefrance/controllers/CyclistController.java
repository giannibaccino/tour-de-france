package com.tdf.tourdefrance.controllers;

import com.tdf.tourdefrance.dtos.CyclistDto;
import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Cyclist;
import com.tdf.tourdefrance.models.Team;
import com.tdf.tourdefrance.services.ICyclistService;
import com.tdf.tourdefrance.services.ITeamService;
import com.tdf.tourdefrance.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/cyclists")
public class CyclistController {

    @Autowired
    private ICyclistService service;
    @Autowired
    private ITeamService teamService;

    @PostMapping("/create")
    public Mono<CyclistDto> create(@RequestBody Cyclist cyclist) {
        return service.create(cyclist);
    }

    //TODO: Validate teamId and cyclistId
    @PostMapping("/addToTeam/{cylcistId}/{teamId}")
    public Mono<TeamDto> addToTeam(@PathVariable("cylcistId") String cylcistId, @PathVariable("teamId") String teamId) {
        return service.addCyclistToTeam(Integer.valueOf(cylcistId), teamId);
    }

    //TODO: Validate teamId and cyclistId
    @PostMapping("/removeFromTeam/{cylcistId}/{teamId}")
    public Mono<TeamDto> removeFromTeam(@PathVariable("cylcistId") String cylcistId, @PathVariable("teamId") String teamId) {
        return service.removeCyclistFromTeam(Integer.valueOf(cylcistId), teamId);
    }

    @PutMapping("/changeName/{cyclistId}")
    public Mono<CyclistDto> updateName(@PathVariable("cyclistId") String cyclistId, @RequestBody String newName) {
        return service.updateName(Integer.valueOf(cyclistId), newName);
    }

    @GetMapping()
    public Flux<CyclistDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/getCyclist/{cyclistId}")
    public Mono<CyclistDto> findById(@PathVariable("cyclistId") String cyclistId) {
        return service.findByCyclistId(Integer.valueOf(cyclistId));
    }

    @GetMapping("/getCyclistsByNationality/{country}")
    public Flux<CyclistDto> findByCountry(@PathVariable("country") String country) {
        return service.findAll().filter(cyclist -> cyclist.getNationality().toLowerCase().equals(country.toLowerCase()));
    }

    @GetMapping("/getCyclistByTeamId/{teamId}")
    public Flux<CyclistDto> findByTeamId(@PathVariable("teamId") String teamId) {
        return Flux.fromIterable((AppUtils.dtoToTeam(teamService.findByTeamId(teamId).block()).getCyclists().stream().map(AppUtils::cyclistToDto).collect(Collectors.toSet())));
    }

    @DeleteMapping("/deleteCyclist/{cyclistId}")
    public Mono<Void> deleteTeam(@PathVariable("cyclistId") String cyclistId) {
        return service.delete(Integer.valueOf(cyclistId));
    }
}
