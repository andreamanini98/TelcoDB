package it.polimi.telcodb.controllers;

import it.polimi.telcodb.enums.ServiceType;
import it.polimi.telcodb.services.EmployeeService;
import it.polimi.telcodb.services.ExceptionFormatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class EmployeePageController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExceptionFormatterService exceptionFormatterService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @RequestMapping("/openEmployeePage")
    public String openEmployeePage() {
        return "employeePage";
    }


    //SOLO PER INSERIRE GLI EMPLOYEE NEL DATABASE, POI DOVRAI ELIMINARE:
    // - QUESTO METODO
    // - IL FORM DA INDEX.HTML
    // - IL METODO RELATIVO NELL'EMPLOYEESERVICE
    // - LA RICHIESTA RELATIVA NEL FILE SECURITYCONFIG
    @PostMapping("/registerEmployee")
    public String registerEmployee(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        employeeService.saveEmployee(username, bCryptPasswordEncoder.encode(password), email);
        return "index";
    }


    @PostMapping("/createFixedPhoneService")
    public String createFixedPhoneService() {
        employeeService.saveFixedPhoneService();
        return "employeePage";
    }


    @PostMapping("/createMobilePhoneService")
    public String createMobilePhoneService(@RequestParam int nMinutes, @RequestParam int nSMSs, @RequestParam BigDecimal extraMinFee, @RequestParam BigDecimal extraSMSFee) {
        employeeService.saveMobilePhoneService(nMinutes, nSMSs, extraMinFee, extraSMSFee);
        return "employeePage";
    }


    @PostMapping("/createFixedInternetService")
    public String createFixedInternetService(@RequestParam int nGiga, @RequestParam BigDecimal extraGigaFee) {
        employeeService.saveFixedMobileInternetService(ServiceType.FIXED_INTERNET, nGiga, extraGigaFee);
        return "employeePage";
    }


    @PostMapping("/createMobileInternetService")
    public String createMobileInternetService(@RequestParam int nGiga, @RequestParam BigDecimal extraGigaFee) {
        employeeService.saveFixedMobileInternetService(ServiceType.MOBILE_INTERNET, nGiga, extraGigaFee);
        return "employeePage";
    }


    @PostMapping("/createValidityPeriod")
    public String createValidityPeriod(@RequestParam int nMonths, @RequestParam BigDecimal monthlyFee) {
        employeeService.saveValidityPeriod(nMonths, monthlyFee);
        return "employeePage";
    }


    @PostMapping("/createOptionalProduct")
    public String createOptionalProduct(@RequestParam String name, @RequestParam BigDecimal monthlyFee) {
        employeeService.saveOptionalProduct(name, monthlyFee);
        return "employeePage";
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ModelAndView handleMissingParams(SQLIntegrityConstraintViolationException e) {
        ModelAndView modelAndView = new ModelAndView("employeePage");
        modelAndView.addObject("errorMessage", exceptionFormatterService.formatSQLGenericElementAlreadyExists(e.getMessage()));
        return modelAndView;
    }

}
