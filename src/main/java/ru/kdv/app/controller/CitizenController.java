package ru.kdv.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kdv.app.dto.CitizenStatisticDto;
import ru.kdv.app.entity.Citizen;
import ru.kdv.app.repository.CitizenRepository;
import ru.kdv.app.service.fines.Fine;
import ru.kdv.app.service.fines.FineClient;

import java.math.BigDecimal;

@RestController
public class CitizenController {

    private final CitizenRepository citizenRepository;
    private final FineClient fineClient;

    @Autowired
    public CitizenController(CitizenRepository citizenRepository, FineClient fineClient) {
        this.citizenRepository = citizenRepository;
        this.fineClient = fineClient;
    }

    @PostMapping("/citizens/")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus saveCitizen(Citizen citizen) {
        try {
            citizenRepository.save(citizen);
        } catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @GetMapping("/citizens/{identification}")
    @ResponseStatus(HttpStatus.OK)
    public CitizenStatisticDto getCitizen(@PathVariable String identification) {
        Citizen citizen = citizenRepository.findByIdNumber(identification)
                .orElseThrow(() -> new RuntimeException("No such citizen"));

        BigDecimal finesTotal = fineClient.getFines(citizen) // rest request
                .stream()
                .filter(fine -> !fine.isPaid())
                .map(Fine::getSum)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .stripTrailingZeros();

        CitizenStatisticDto citizenStatisticDto = new CitizenStatisticDto();
        citizenStatisticDto.firstName = citizen.getFirstName();
        citizenStatisticDto.lastName = citizen.getLastName();
        citizenStatisticDto.finesTotal = finesTotal;
        return citizenStatisticDto;
    }

}