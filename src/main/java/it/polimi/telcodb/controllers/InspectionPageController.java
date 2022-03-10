package it.polimi.telcodb.controllers;

import it.polimi.telcodb.services.InspectionPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InspectionPageController {

    @Autowired
    private InspectionPageService inspectionPageService;


    @RequestMapping("/openInspectionPage")
    public ModelAndView openInspectionPage() {
        ModelAndView modelAndView = new ModelAndView("inspectionPage");
        insertMaterializationTriggerParameters(modelAndView);
        return modelAndView;
    }


    private void insertMaterializationTriggerParameters(ModelAndView modelAndView) {
        modelAndView.addObject("ANOOPWSPs", inspectionPageService.findAllANOOPWSPs());
        modelAndView.addObject("BSOPs", inspectionPageService.findAllBSOPs());
        modelAndView.addObject("TPPSPs", inspectionPageService.findAllTPPSPs());
        modelAndView.addObject("TPPSPAVPs", inspectionPageService.findAllTPPSPAVPs());
        modelAndView.addObject("TSVOSPs", inspectionPageService.findAllTSVOSPs());
        modelAndView.addObject("InsolventUsers", inspectionPageService.findAllInsolvents());
        modelAndView.addObject("InvalidOrders", inspectionPageService.findAllInvalids());
        modelAndView.addObject("Alerts", inspectionPageService.findAllAlerts());
    }

}
