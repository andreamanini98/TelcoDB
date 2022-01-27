package it.polimi.telcodb.controllers;

import it.polimi.telcodb.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeePageController {

    @Autowired
    EmployeeService employeeService;

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
    @RequestMapping("/registerEmployee")
    public String registerEmployee(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        employeeService.saveEmployee(username, bCryptPasswordEncoder.encode(password), email);
        return "index";
    }

}
