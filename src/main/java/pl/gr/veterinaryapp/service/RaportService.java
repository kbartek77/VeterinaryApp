package pl.gr.veterinaryapp.service;

import pl.gr.veterinaryapp.model.dto.RaportDto;

import java.time.YearMonth;

public interface RaportService {
    RaportDto getRaport(YearMonth yearMonth);
}
