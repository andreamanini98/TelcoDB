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
        ModelAndView modelAndView = new ModelAndView("orderCreatedPage");
        orderManagerService.createOrder(principal.getName(), sessionService.getServicePackageOrder(session));
        session.removeAttribute("spO");
        return modelAndView;
    }

    // nel metodo che ti crea l'ordine passagli un booleano che ti fa un ordine giusto o sbagliato in base al suo valore
    // fai comunque un altro metodo che ti prende la richiesta di fare un ordine sbagliato o vedi come conviene di più
    // metti che se l'ordine è giusto ti sposti nella pagina ordercreatedpage e mostri qualche avviso di correttezza, altrimenti lo
    // stesso ma con l'ordine che è fallito

}
