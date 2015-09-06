package otts.test.work.service;

import otts.test.work.messages.Alert;

import java.util.List;

/**
 * Created by alex on 05.09.2015.<br/>
 * Service that provides alerts to user
 */
public interface AlertService {

    /**
     * Gets currently available alerts to show
     * @return list of alerts
     */
    List<Alert> getAvailableAlerts();
}
