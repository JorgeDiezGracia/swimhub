package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.TimeRecord;
import com.svalero.swimhub.domain.dto.PerformanceTrendDto;
import com.svalero.swimhub.exception.SwimmerNotFoundException;
import com.svalero.swimhub.repository.SwimmerRepository;
import com.svalero.swimhub.repository.TimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceTrendService {

    private final TimeRecordRepository timeRecordRepository;
    private final SwimmerRepository swimmerRepository;

    public PerformanceTrendDto analyze(Long swimmerId, Long raceId) throws SwimmerNotFoundException {

        swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new SwimmerNotFoundException(
                        "Swimmer not found with id: " + swimmerId));

        List<TimeRecord> records = timeRecordRepository
                .findBySwimmerIdAndRaceId(swimmerId, raceId, Pageable.unpaged())
                .getContent();

        if (records.isEmpty()) {
            PerformanceTrendDto dto = new PerformanceTrendDto();
            dto.setSwimmerId(swimmerId);
            dto.setRaceId(raceId);
            dto.setTotalRecords(0);
            dto.setTrend("SIN DATOS");
            return dto;
        }

        double bestTime = records.stream()
                .mapToDouble(TimeRecord::getTime)
                .min().orElse(0);

        double worstTime = records.stream()
                .mapToDouble(TimeRecord::getTime)
                .max().orElse(0);

        double averageTime = records.stream()
                .mapToDouble(TimeRecord::getTime)
                .average().orElse(0);

        double firstTime = records.get(0).getTime();
        double lastTime = records.get(records.size() - 1).getTime();

        double improvementPercentage = ((firstTime - lastTime) / firstTime) * 100;

        String trend;
        if (improvementPercentage > 1) {
            trend = "MEJORANDO";
        } else if (improvementPercentage < -1) {
            trend = "EMPEORANDO";
        } else {
            trend = "ESTABLE";
        }

        PerformanceTrendDto dto = new PerformanceTrendDto();
        dto.setSwimmerId(swimmerId);
        dto.setSwimmerName(records.get(0).getSwimmer().getName());
        dto.setSwimmerSurname(records.get(0).getSwimmer().getSurname());
        dto.setRaceId(raceId);
        dto.setRaceDescription(records.get(0).getRace().getStyle() + " " +
                records.get(0).getRace().getDistance() + "m " +
                records.get(0).getRace().getPoolType());
        dto.setBestTime(bestTime);
        dto.setWorstTime(worstTime);
        dto.setAverageTime(Math.round(averageTime * 100.0) / 100.0);
        dto.setFirstTime(firstTime);
        dto.setLastTime(lastTime);
        dto.setImprovementPercentage(Math.round(improvementPercentage * 100.0) / 100.0);
        dto.setTrend(trend);
        dto.setTotalRecords(records.size());

        return dto;
    }
}
