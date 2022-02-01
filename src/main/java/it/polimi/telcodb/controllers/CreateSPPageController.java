package it.polimi.telcodb.controllers;

import it.polimi.telcodb.enums.ServiceType;
import it.polimi.telcodb.services.EmployeeService;
import it.polimi.telcodb.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CreateSPPageController {

    @Autowired
    QueryService queryService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/openCreateSPPage")
    public ModelAndView openCreateSPPage() {
        ModelAndView modelAndView = new ModelAndView("createSPPage");
        addParametersToModelAndView(modelAndView);

        return modelAndView;
    }


    //TODO implementa un filtro che vede se tutti e 4 i servizi sono null e nel caso lancia un'eccezione
    @RequestMapping("/createServicePackage")
    public ModelAndView printResultList(
            @RequestParam String name,
            @RequestParam(required = false) List<String> selectedOP,
            @RequestParam List<String> selectedVP,
            @RequestParam(required = false) String selectedFP,
            @RequestParam(required = false) String selectedMP,
            @RequestParam(required = false) String selectedFI,
            @RequestParam(required = false) String selectedMI
    ) {
        ModelAndView modelAndView = new ModelAndView("createSPPage");
        addParametersToModelAndView(modelAndView);

        List<String> selectedS = createServicesList(selectedFP, selectedMP, selectedFI, selectedMI);

        employeeService.saveServicePackage(name, selectedOP, selectedVP, selectedS);

        return modelAndView;
    }


    private void addParametersToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("optionalProductsList", queryService.findAllOptionalProducts());
        modelAndView.addObject("validityPeriodsList", queryService.findAllValidityPeriod());
        modelAndView.addObject("fixedPhoneServicesList", queryService.findServiceByServiceType(ServiceType.FIXED_PHONE));
        modelAndView.addObject("mobilePhoneServicesList", queryService.findServiceByServiceType(ServiceType.MOBILE_PHONE));
        modelAndView.addObject("fixedInternetServicesList", queryService.findServiceByServiceType(ServiceType.FIXED_INTERNET));
        modelAndView.addObject("mobileInternetServicesList", queryService.findServiceByServiceType(ServiceType.MOBILE_INTERNET));
    }


    private List<String> createServicesList(String service1, String service2, String service3, String service4) {
        List<String> selectedS = new ArrayList<>();
        selectedS.add(service1);
        selectedS.add(service2);
        selectedS.add(service3);
        selectedS.add(service4);
        selectedS.removeIf(Objects::isNull);
        return selectedS;
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException e) {
        ModelAndView modelAndView = new ModelAndView("createSPPage");
        addParametersToModelAndView(modelAndView);

        String name = e.getParameterName();
        System.out.println(name + " parameter is missing");
        return modelAndView;
    }

}
