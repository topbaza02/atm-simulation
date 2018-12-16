package com.test.atm.controller;

import com.test.atm.controller.config.ControllerBase;
import com.test.atm.controller.service.ATMService;
import com.test.atm.dto.Account;
import com.test.atm.dto.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Controller
public class ATMServiceController extends ControllerBase {

    private final Logger logger = Logger.getLogger(getClass().getName());



    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute(name = "account") Account account, Model model) {
        logger.info("#------ initial -----: " + account);
        Database.account = account;
        return new ModelAndView("redirect:/home");
    }


    @PostMapping(value = "/withdraw")
    public ModelAndView withdraw(@RequestParam("amount") BigDecimal amount, Model model) {
        logger.info("#-------withdraw  : " + amount);
        service.withdraw(amount);
        return new ModelAndView("redirect:/home");
    }

}
