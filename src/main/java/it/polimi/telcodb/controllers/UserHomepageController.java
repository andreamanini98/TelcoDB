package it.polimi.telcodb.controllers;

import it.polimi.telcodb.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.SimpleDateFormat;

@Controller
public class UserHomepageController {

    @Autowired
    private QueryService queryService;


    @RequestMapping("/openUserHomePageLogged")
    public ModelAndView openUserHomePageLogged(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        addInvalidOrdersInfoToModelAndView(modelAndView, principal.getName());
        return modelAndView;
    }


    @RequestMapping("/openUserHomePage")
    public ModelAndView openUserHomePage() {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        return modelAndView;
    }


    @PostMapping("/showServicePackageInfo")
    public ModelAndView showServicePackageInfo(Principal principal, @RequestParam(name = "Service Package") String servicePackageId) {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        if (principal != null) addInvalidOrdersInfoToModelAndView(modelAndView, principal.getName());
        addServicePackageInfoToModelAndView(modelAndView, servicePackageId);
        return modelAndView;
    }


    @PostMapping("/tryServicePackagePaymentAgain")
    public ModelAndView tryServicePackagePaymentAgain(@RequestParam(name = "Order") String orderId) {
        ModelAndView modelAndView = new ModelAndView("confirmationPage");
        modelAndView.addObject("isTryingAgain", true);
        modelAndView.addObject("dateFormatter", new SimpleDateFormat("dd/MM/yyyy"));
        modelAndView.addObject("orderToBuyAgain", queryService.findUserOrderById(orderId));
        return modelAndView;
    }


    private void addParametersToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("servicePackageList", queryService.findAllServicePackages());
    }


    private void addServicePackageInfoToModelAndView(ModelAndView modelAndView, String servicePackageId) {
        modelAndView.addObject("showInfo", true);
        modelAndView.addObject("servicePackageToShow", queryService.findServicePackageById(servicePackageId));
    }


    private void addInvalidOrdersInfoToModelAndView(ModelAndView modelAndView, String username) {
        if (username != null) {
            if (queryService.getIsInsolventByUsername(username)) {
                modelAndView.addObject("areOrdersInvalid", true);
                modelAndView.addObject("invalidOrders", queryService.getInvalidOrdersByUsername(username));
                modelAndView.addObject("totalCostToPay", queryService.getSumOfAllInvalidOrdersCostByUsername(username));
            }
        }
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException e, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        if (principal != null) addInvalidOrdersInfoToModelAndView(modelAndView, principal.getName());
        modelAndView.addObject("errorMessage", e.getParameterName() + " parameter is missing!");
        return modelAndView;
    }

}
