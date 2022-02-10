package it.polimi.telcodb.controllers;

import it.polimi.telcodb.model.ServicePackageOrder;
import it.polimi.telcodb.services.ConfirmationPageService;
import it.polimi.telcodb.services.QueryService;
import it.polimi.telcodb.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BuyServicePackagePageController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ConfirmationPageService confirmationPageService;


    @RequestMapping("/openBuyServicePackagePage")
    public ModelAndView openBuyServicePackagePage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("buyServicePackagePage");
        addServicePackageIfAlreadySelected(modelAndView, session);
        return modelAndView;
    }


    @PostMapping("/selectServicePackageToBuy")
    public ModelAndView selectServicePackageToBuy(HttpSession session, @RequestParam(name = "Service Package") String servicePackageId) {
        ModelAndView modelAndView = new ModelAndView("buyServicePackagePage");
        sessionService.addServicePackageToServicePackageOrder(servicePackageId, session);
        addServicePackageIfAlreadySelected(modelAndView, session);
        return modelAndView;
    }


    @RequestMapping("/resetBuyServicePackagePage")
    public ModelAndView resetBuyServicePackagePage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("buyServicePackagePage");
        sessionService.resetServicePackageOrder(session);
        addServicePackageIfAlreadySelected(modelAndView, session);
        return modelAndView;
    }


    @PostMapping("/openConfirmationPage")
    public ModelAndView openConfirmationPage(
            HttpSession session,
            @RequestParam(required = false) List<String> selectedOP,
            @RequestParam(name = "Validity Period") String validityPeriod,
            @RequestParam String subscriptionDate
    ) {
        ModelAndView modelAndView = new ModelAndView("confirmationPage");
        ServicePackageOrder spO = sessionService.addSelectedItemsToServicePackageOrder(session, selectedOP, validityPeriod, subscriptionDate);
        modelAndView.addObject("servicePackageOrder", spO);
        modelAndView.addObject("totalCost", confirmationPageService.computeTotalCost(spO.getValidityPeriod(), spO.getOptionalProducts()));
        return modelAndView;
    }


    @RequestMapping("/openConfirmationPageRegistered")
    public ModelAndView openConfirmationPageRegistered(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("confirmationPage");
        ServicePackageOrder spO = sessionService.getServicePackageOrder(session);
        modelAndView.addObject("servicePackageOrder", spO);
        modelAndView.addObject("totalCost", confirmationPageService.computeTotalCost(spO.getValidityPeriod(), spO.getOptionalProducts()));
        return modelAndView;
    }


    private void addServicePackageIfAlreadySelected(ModelAndView modelAndView, HttpSession session) {
        boolean isServicePackageInOrder = sessionService.isServicePackageAlreadySelected(session);
        modelAndView.addObject("isServicePackageSelected", isServicePackageInOrder);
        if (isServicePackageInOrder)
            modelAndView.addObject("selectedServicePackage", sessionService.getServicePackageOrder(session).getServicePackage());
        else modelAndView.addObject("servicePackageList", queryService.findAllServicePackages());
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(HttpSession session, MissingServletRequestParameterException e) {
        ModelAndView modelAndView = new ModelAndView("buyServicePackagePage");
        addServicePackageIfAlreadySelected(modelAndView, session);
        modelAndView.addObject("errorMessage", e.getParameterName() + " parameter is missing!");
        return modelAndView;
    }

}
