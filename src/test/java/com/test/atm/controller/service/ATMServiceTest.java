package com.test.atm.controller.service;

import com.test.atm.dto.Account;
import com.test.atm.dto.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ATMServiceTest {

    @Autowired
    private ATMService service;

    @Test
    public void withdraw5000ByFive1000Banknotes() {
        Database.account = new Account();
        Database.account.setBanknotes_20(BigDecimal.valueOf(20));
        Database.account.setBanknotes_100(BigDecimal.valueOf(20));
        Database.account.setBanknotes_1000(BigDecimal.valueOf(20));
        Database.account.setBanknotes_500(BigDecimal.valueOf(20));
        Database.account.setBanknotes_50(BigDecimal.valueOf(20));
        Database.account.setBalance(BigDecimal.valueOf(50000));
        service.withdraw(BigDecimal.valueOf(5000));

        assertEquals(BigDecimal.valueOf(45000),Database.account.getBalance());
        assertEquals(BigDecimal.valueOf(15),Database.account.getBanknotes_1000());


    }

}