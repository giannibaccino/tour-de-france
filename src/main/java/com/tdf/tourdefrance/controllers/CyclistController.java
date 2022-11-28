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
        if(cyclist.getCyclistId() <= 999)
            return service.create(cyclist);
        return Mono.error(new Error("Invalid Id (1-999)"));
    }

    //TODO: Validate teamId and cyclistId
    @PostMapping("/addToTeam/{cylcistId}/{teamId}")
    public Mono<TeamDto> addToTeam(@PathVariable("cylcistId") String cylcistId, @PathVariable("teamId") String teamId) {
        if(Integer.valueOf(cylcistId) <= 999) {
            if(teamId.matches("^[a-zA-Z0-9]*$"))
                return service.addCyclistToTeam(Integer.valueOf(cylcistId), teamId);
            return Mono.error(new Error("Team Id does not exist (alphanumeric)"));
        }
        return Mono.error(new Error("Cyclist Id does not exist (1-999)"));
    }

    //TODO: Validate teamId and cyclistId
    @PostMapping("/removeFromTeam/{cylcistId}/{teamId}")
    public Mono<TeamDto> removeFromTeam(@PathVariable("cylcistId") String cylcistId, @PathVariable("teamId") String teamId) {
        if(Integer.valueOf(cylcistId) <= 999) {
            if (teamId.matches("^[a-zA-Z0-9]*$"))
                return service.removeCyclistFromTeam(Integer.valueOf(cylcistId), teamId);
            return Mono.error(new Error("Team Id does not exist (alphanumeric)"));
        }
        return Mono.error(new Error("Cyclist Id does not exist (1-999)"));
    }

    @PutMapping("/changeName/{cyclistId}")
    public Mono<CyclistDto> updateName(@PathVariable("cyclistId") String cyclistId, @RequestBody String newName) {
        if(Integer.valueOf(cyclistId) <= 999)
            return service.updateName(Integer.valueOf(cyclistId), newName);
        return Mono.error(new Error("Cyclist Id does not exist (1-999)"));
    }

    @GetMapping()
    public Flux<CyclistDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/getCyclist/{cyclistId}")
    public Mono<CyclistDto> findById(@PathVariable("cyclistId") String cyclistId) {
        if(Integer.valueOf(cyclistId) <= 999)
            return service.findByCyclistId(Integer.valueOf(cyclistId));
        return Mono.error(new Error("Cyclist Id does not exist (1-999)"));
    }

    @GetMapping("/getCyclistsByNationality/{country}")
    public Flux<CyclistDto> findByCountry(@PathVariable("country") String country) {
        return service.findAll().filter(cyclist -> cyclist.getNationality().toLowerCase().equals(country.toLowerCase()));
    }

    @GetMapping("/getCyclistByTeamId/{teamId}")
    public Flux<CyclistDto> findByTeamId(@PathVariable("teamId") String teamId) {
        if (teamId.matches("^[a-zA-Z0-9]*$"))
            return Flux.fromIterable((AppUtils.dtoToTeam(teamService.findByTeamId(teamId).block()).getCyclists().stream().map(AppUtils::cyclistToDto).collect(Collectors.toSet())));
        return Flux.error(new Error("Team Id does not exist (alphanumeric)"));
    }

    @DeleteMapping("/deleteCyclist/{cyclistId}")
    public Mono<Void> deleteTeam(@PathVariable("cyclistId") String cyclistId) {
        if(Integer.valueOf(cyclistId) <= 999)
            return service.delete(Integer.valueOf(cyclistId));
        return Mono.error(new Error("Cyclist Id does not exist (1-999)"));
    }
}
