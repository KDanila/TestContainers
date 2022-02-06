package ru.kdv.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kdv.app.entity.Citizen;

import java.util.Optional;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByIdNumber(String identificationNumber);
}