package com.tdf.tourdefrance.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CyclistDto {
    private Integer cyclistId;
    private String fullName;
    private String nationality;
    private String teamName;
}
