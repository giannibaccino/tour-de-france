package com.tdf.tourdefrance.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "cyclists")
public class Cyclist {

    @Id
    private Integer cyclistId;
    private String fullName;
    private String nationality;
    private String teamName;

    public Integer getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(Integer cyclistId) {
        this.cyclistId = cyclistId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cyclist cyclist = (Cyclist) o;
        return Objects.equals(cyclistId, cyclist.cyclistId) && Objects.equals(fullName, cyclist.fullName) && Objects.equals(nationality, cyclist.nationality) && Objects.equals(teamName, cyclist.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, fullName, nationality, teamName);
    }
}
