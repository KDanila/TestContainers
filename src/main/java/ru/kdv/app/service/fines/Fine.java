package ru.kdv.app.service.fines;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Fine {

    private BigDecimal sum;
    private Date date;
    private boolean paid;
}