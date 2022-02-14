package it.polimi.telcodb.controllers;

import it.polimi.telcodb.services.OrderManagerService;
import it.polimi.telcodb.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @RequestMapping("/buyServicePackageCorrectAgain")
    public ModelAndView buyServicePackageCorrectAgain(Principal principal, @RequestParam String orderId) {
        return addParametersToOrderCreatedPageTryingAgain(principal.getName(), orderId, true);
    }


    @RequestMapping("/buyServicePackageInvalidAgain")
    public ModelAndView buyServicePackageInvalidAgain(Principal principal, @RequestParam String orderId) {
        return addParametersToOrderCreatedPageTryingAgain(principal.getName(), orderId, false);
    }


    private ModelAndView addParametersToOrderCreatedPage(String username, HttpSession session, boolean isOrderValid) {
        ModelAndView modelAndView = new ModelAndView("orderCreatedPage");
        boolean isAlertCreated = orderManagerService.createOrderAndAlert(username, sessionService.getServicePackageOrder(session), isOrderValid);

        modelAndView.addObject("isAlertCreated", isAlertCreated);
        modelAndView.addObject("isOrderValid", isOrderValid);
        session.removeAttribute("spO");
        return modelAndView;
    }


    private ModelAndView addParametersToOrderCreatedPageTryingAgain(String username, String orderId, boolean isOrderValid) {
        ModelAndView modelAndView = new ModelAndView("orderCreatedPage");
        boolean isAlertCreated = orderManagerService.tryPaymentAgainAndAlert(username, orderId, isOrderValid);

        modelAndView.addObject("isAlertCreated", isAlertCreated);
        modelAndView.addObject("isOrderValid", isOrderValid);
        return modelAndView;
    }

}
