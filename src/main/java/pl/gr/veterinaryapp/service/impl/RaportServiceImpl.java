package pl.gr.veterinaryapp.service.impl;


import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.Pair;
import org.springframework.stereotype.Service;
import pl.gr.veterinaryapp.model.dto.RaportDto;
import pl.gr.veterinaryapp.model.entity.Vet;
import pl.gr.veterinaryapp.model.entity.Visit;
import pl.gr.veterinaryapp.repository.VetRepository;
import pl.gr.veterinaryapp.repository.VisitRepository;
import pl.gr.veterinaryapp.service.RaportService;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaportServiceImpl implements RaportService {
    private final VisitRepository visitRepository;
    private final VetRepository vetRepository;

    public RaportDto getRaport(YearMonth yearMonth) {

        List<Visit> visitsInMonth = visitRepository.findByYearAndMonth(yearMonth);

        List<Vet> allVets = vetRepository.findAll();

        BigDecimal income = visitsInMonth.stream()
                .map(Visit::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int numberOfVisits = visitsInMonth.size();

        List<Pair<String, Integer>> numberOfVisitsForEachVet = allVets.stream()
                .map(vet -> {
                    int vetVisits = (int) visitsInMonth.stream()
                            .filter(visit -> vet.getId().equals(visit.getVet().getId()))
                            .count();
                    return Pair.of(vet.getName() + " " + vet.getSurname(), vetVisits);
                })
                .collect(Collectors.toList());

        return RaportDto.builder()
                .income(income)
                .numberOfVisits(numberOfVisits)
                .numberOfVisitsForEachVet(numberOfVisitsForEachVet)
                .build();
    }
}
