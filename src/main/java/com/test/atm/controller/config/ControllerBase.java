package com.test.atm.controller.config;

import com.test.atm.controller.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;

public class ControllerBase {


    @Autowired
    protected ATMService service;
}
