package pl.gr.veterinaryapp.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.gr.veterinaryapp.model.dto.RaportDto;
import pl.gr.veterinaryapp.service.RaportService;

import java.time.YearMonth;

@RestController
@RequiredArgsConstructor
public class RaportController {
    private final RaportService raportService;

    @GetMapping("api/raport")
    RaportDto getRaport(@RequestBody YearMonth yearMonth) {
        return raportService.getRaport(yearMonth);
    }
}
