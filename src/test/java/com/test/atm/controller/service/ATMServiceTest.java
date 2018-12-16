package com.test.atm.controller.service;

import com.test.atm.dto.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ATMServiceTest {

    @Autowired
    private ATMService service;

    @Test
    public void calSum() {
        Account account = new Account();

        BigDecimal sum = service.calSum(account);
        assertEquals(BigDecimal.valueOf(16700), sum);

    }

    @Test
    public void calByEachType() {
        BigDecimal sum =  service.calByEachType(BigDecimal.valueOf(20), BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(1000), sum);
    }
}