package ru.kdv.app.service.fines;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kdv.app.entity.Citizen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FineClient {

    @Value("${service-fine.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Fine> getFines(Citizen citizen) {
        Fine[] fines = restTemplate.getForEntity(baseUrl + "/fines/" + citizen.getId(), Fine[].class).getBody();
        if (fines == null || fines.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(fines);
    }
}