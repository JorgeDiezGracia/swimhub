package com.svalero.swimhub.domain.dto;

import lombok.Data;

@Data
public class PerformanceTrendDto {

    private Long swimmerId;
    private String swimmerName;
    private String swimmerSurname;
    private Long raceId;
    private String raceDescription;
    private Double bestTime;
    private Double worstTime;
    private Double averageTime;
    private Double firstTime;
    private Double lastTime;
    private Double improvementPercentage;
    private String trend;
    private Integer totalRecords;
}