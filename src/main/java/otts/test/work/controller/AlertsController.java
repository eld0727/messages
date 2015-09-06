package otts.test.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.messages.Alert;
import otts.test.work.service.AlertService;

import java.util.List;

/**
 * Created by alex on 06.09.2015.<br/>
 * Controller returning alerts
 */
@Controller
public class AlertsController {

    @Autowired
    private AlertService alertService;

    @ResponseBody
    @RequestMapping(value = "/alerts", method = RequestMethod.GET)
    public List<Alert> getAlerts() {
        return alertService.getAvailableAlerts();
    }
}
