package com.tdf.tourdefrance.dtos;

import com.tdf.tourdefrance.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String teamId;
    private String teamName;
    private String country;
    private Set<Cyclist> cyclists;
}
