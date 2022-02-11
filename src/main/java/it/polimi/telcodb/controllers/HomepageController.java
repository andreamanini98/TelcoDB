package it.polimi.telcodb.controllers;

import it.polimi.telcodb.services.ExceptionFormatterService;
import it.polimi.telcodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class HomepageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExceptionFormatterService exceptionFormatterService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @RequestMapping("/")
    public String getHomePage() {
        return "index";
    }


    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        userService.saveUser(username, email, bCryptPasswordEncoder.encode(password));
        return "index";
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ModelAndView handleMissingParams(SQLIntegrityConstraintViolationException e) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("errorMessage", exceptionFormatterService.formatSQLUserAlreadyExists(e.getMessage()));
        return modelAndView;
    }

}
