package it.polimi.telcodb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {

    @RequestMapping("/")
    public String getHomePage() {
        return "index";
    }


    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }


    @RequestMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logout";
    }

}
