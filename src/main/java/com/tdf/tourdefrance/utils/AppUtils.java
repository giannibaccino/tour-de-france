package com.tdf.tourdefrance.utils;

import com.tdf.tourdefrance.dtos.CyclistDto;
import com.tdf.tourdefrance.dtos.TeamDto;
import com.tdf.tourdefrance.models.Cyclist;
import com.tdf.tourdefrance.models.Team;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Flux;

public class AppUtils {

    public static CyclistDto cyclistToDto(Cyclist cyclist){
        CyclistDto cyclistDto = new CyclistDto();
        BeanUtils.copyProperties(cyclist, cyclistDto);
        return cyclistDto;
    }

    public static Cyclist dtoToCyclist(CyclistDto cyclistDto){
        Cyclist cyclist = new Cyclist();
        BeanUtils.copyProperties(cyclistDto, cyclist);
        return cyclist;
    }

    public static Flux<CyclistDto> cyclistListToDto(Flux<Cyclist> cyclistFlux) {
        Flux<CyclistDto> cyclistDtoFlux = cyclistFlux.map(AppUtils::cyclistToDto);
        return cyclistDtoFlux;
    }

    public static Flux<Cyclist> dtoListToCyclist(Flux<CyclistDto> cyclistDtoFlux) {
        Flux<Cyclist> cyclistFlux = cyclistDtoFlux.map(AppUtils::dtoToCyclist);
        return cyclistFlux;
    }

    public static TeamDto teamToDto(Team team){
        TeamDto teamDto = new TeamDto();
        BeanUtils.copyProperties(team, teamDto);
        return teamDto;
    }

    public static Team dtoToTeam(TeamDto teamDto){
        Team team = new Team();
        BeanUtils.copyProperties(teamDto, team);
        return team;
    }

    public static Flux<TeamDto> teamListToDto(Flux<Team> teamFlux) {
        Flux<TeamDto> teamDtoFlux = teamFlux.map(AppUtils::teamToDto);
        return teamDtoFlux;
    }

    public static Flux<Team> dtoListToTeam(Flux<TeamDto> teamDtoFlux) {
        Flux<Team> teamFlux = teamDtoFlux.map(AppUtils::dtoToTeam);
        return teamFlux;
    }
}
