package ru.kdv.app.controller;

import org.junit.jupiter.api.Test;
import org.mockserver.client.server.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.kdv.app.dto.CitizenStatisticDto;
import ru.kdv.app.entity.Citizen;

import java.math.BigDecimal;

import static java.util.jar.Attributes.Name.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest
@Sql( scripts = "classpath:init-citizens.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Testcontainers
class CitizenControllerTest  {

    @Autowired
    private CitizenController citizenController;
//
//    @LocalServerPort
//    private int port;
////
//    @Autowired
//    private TestRestTemplate restTemplate;

    public static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:latest");

    @Container
    private static final GenericContainer<?> postgres =
            new GenericContainer<>(POSTGRES_IMAGE);


    @Test
    void postgresInitTest() {
        Citizen citizen = Citizen.builder()
                .firstName("first")
                .lastName("last")
                .idNumber("111")
                .build();
        HttpStatus httpStatus = citizenController.saveCitizen(citizen);

        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
    }



    @Test
    void getCitizenStatistic_1() {

//        new MockServerClient(postgres.getHost(), postgres.getFirstMappedPort())
//                .when(request()
//                        .withPath("/fines/1"))
//                .respond(response()
//                        .withBody("[]").withHeader(String.valueOf(CONTENT_TYPE), APPLICATION_JSON_VALUE));
//
//        ResponseEntity<CitizenStatisticDto> entity = restTemplate
//                .getForEntity("http://localhost:" + port + "/citizens/98y783y7438uyegbkyg37yrg", CitizenStatisticDto.class);
//
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(entity.getBody()).isNotNull();
//        assertThat(entity.getBody().finesTotal).isEqualTo(BigDecimal.ZERO);
    }
//
//    @Test
//    void getCitizenStatistic_forMikeBerlin() {
//
//        new MockServerClient(mockServer.getHost(), mockServer.getServerPort())
//                .when(request()
//                        .withPath("/fines/2"))
//                .respond(response()
//                        .withBody("[{\"sum\":1499.50}]").withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE));
//
//        ResponseEntity<CitizenStatisticDto> entity = restTemplate
//                .getForEntity("http://localhost:" + port + "/citizens/9284hf294fb29248fkds", CitizenStatisticDto.class);
//
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(entity.getBody()).isNotNull();
//        assertThat(entity.getBody().finesTotal).isEqualTo(BigDecimal.valueOf(1499.50));
//    }
//
//    @Test
//    void getCitizenStatistic_forUnknown() {
//        ResponseEntity<CitizenStatisticDto> entity = restTemplate
//                .getForEntity("http://localhost:" + port + "/citizens/1", CitizenStatisticDto.class);
//
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
}