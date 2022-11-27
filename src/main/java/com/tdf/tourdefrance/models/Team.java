package com.tdf.tourdefrance.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "teams")
public class Team {

    @Id
    private String teamId;
    private String teamName;
    private String country;
    private Set<Cyclist> cyclists = new HashSet<>();

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Cyclist> getCyclists() {
        return cyclists;
    }

    public void setCyclists(Set<Cyclist> cyclists) {
        this.cyclists = cyclists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId) && Objects.equals(teamName, team.teamName) && Objects.equals(country, team.country) && Objects.equals(cyclists, team.cyclists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, teamName, country, cyclists);
    }
}
