package it.polimi.telcodb.controllers;

import it.polimi.telcodb.model.ServicePackageOrder;
import it.polimi.telcodb.services.ExceptionFormatterService;
import it.polimi.telcodb.services.SessionService;
import it.polimi.telcodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class ConfirmationPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ExceptionFormatterService exceptionFormatterService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @RequestMapping("/openConfirmationPageRegistered")
    public ModelAndView openConfirmationPageRegistered(HttpSession session) {
        return addParametersToConfirmationPage(session);
    }


    @RequestMapping("/registerBeforeBuyingServicePackage")
    public ModelAndView registerBeforeBuyingServicePackage() {
        ModelAndView modelAndView = new ModelAndView("registerBeforePurchasePage");
        modelAndView.addObject("isUserRegistered", false);
        return modelAndView;
    }


    @PostMapping("/registerUserBeforePurchase")
    public ModelAndView registerUserBeforePurchase(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView("registerBeforePurchasePage");
        userService.saveUser(username, email, bCryptPasswordEncoder.encode(password));
        modelAndView.addObject("isUserRegistered", true);
        return modelAndView;
    }


    private ModelAndView addParametersToConfirmationPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("confirmationPage");
        ServicePackageOrder spO = sessionService.getServicePackageOrder(session);
        modelAndView.addObject("servicePackageOrder", spO);
        modelAndView.addObject("totalCost", spO.computeTotalCost());
        return modelAndView;
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ModelAndView handleMissingParams(HttpSession session, SQLIntegrityConstraintViolationException e) {
        ModelAndView modelAndView = addParametersToConfirmationPage(session);
        modelAndView.addObject("errorMessage", exceptionFormatterService.formatSQLUserAlreadyExists(e.getMessage()));
        return modelAndView;
    }

}
