package com.test.atm.controller;

import com.test.atm.controller.config.ControllerBase;
import com.test.atm.controller.service.ATMService;
import com.test.atm.dto.Account;
import com.test.atm.dto.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class ATMViewController extends ControllerBase {

    private final Logger logger = Logger.getLogger(getClass().getName());


    @GetMapping("/initial")
    public String initial(Model model) {
        logger.info("#------ initial -----#");
        model.addAttribute("account",  new Account());
        return "initial";
    }

    @GetMapping("/home")
    public String home(Model model) {
        logger.info("#-------home-------#");
        if (null == Database.account) {
            return initial(model);
        }

        model.addAttribute("balance", Database.account.getBalance());
        model.addAttribute("account", Database.account);
        model.addAttribute("msg",Database.msg);
        return "home";
    }


}
