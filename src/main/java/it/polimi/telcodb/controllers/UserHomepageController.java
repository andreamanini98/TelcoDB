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

@Controller
public class UserHomepageController {

    @Autowired
    QueryService queryService;


    @RequestMapping("/openUserHomePageLogged")
    public ModelAndView openUserHomePageLogged() {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        return modelAndView;
    }


    @RequestMapping("/openUserHomePage")
    public ModelAndView openUserHomePage() {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        return modelAndView;
    }


    @PostMapping("/showServicePackageInfo")
    public ModelAndView showServicePackageInfo(@RequestParam(name = "Service Package") String servicePackageId) {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        addServicePackageInfoToModelAndView(modelAndView, servicePackageId);
        return modelAndView;
    }


    private void addParametersToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("servicePackageList", queryService.findAllServicePackages());
    }


    private void addServicePackageInfoToModelAndView(ModelAndView modelAndView, String servicePackageId) {
        modelAndView.addObject("showInfo", true);
        modelAndView.addObject("servicePackageToShow", queryService.findServicePackageById(servicePackageId));
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException e) {
        ModelAndView modelAndView = new ModelAndView("userHomepage");
        addParametersToModelAndView(modelAndView);
        modelAndView.addObject("errorMessage", e.getParameterName() + " parameter is missing!");
        return modelAndView;
    }

}
