package it.polimi.telcodb.controllers;

import it.polimi.telcodb.services.OrderManagerService;
import it.polimi.telcodb.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class OrderManagerController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private OrderManagerService orderManagerService;


    @RequestMapping("/buyServicePackageCorrect")
    public ModelAndView buyServicePackageCorrect(Principal principal, HttpSession session) {
        return addParametersToOrderCreatedPage(principal.getName(), session, true);
    }


    @RequestMapping("/buyServicePackageInvalid")
    public ModelAndView buyServicePackageInvalid(Principal principal, HttpSession session) {
        return addParametersToOrderCreatedPage(principal.getName(), session, false);
    }


    private ModelAndView addParametersToOrderCreatedPage(String username, HttpSession session, boolean isOrderValid) {
        ModelAndView modelAndView = new ModelAndView("orderCreatedPage");
        orderManagerService.createOrder(username, sessionService.getServicePackageOrder(session), isOrderValid);
        session.removeAttribute("spO");
        modelAndView.addObject("isOrderValid", isOrderValid);
        return modelAndView;
    }

}
