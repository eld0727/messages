package otts.test.work.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import otts.test.work.messages.Alert;
import otts.test.work.service.AlertService;
import otts.test.work.util.QueueNames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by alex on 05.09.2015.<br/>
 * Implementation for {@link AlertService} uses RabbitMQ alert query
 */
@Service
public class AlertServiceImpl implements AlertService {

    /**
     * List of current alerts
     */
    private final List<Alert> alerts = new ArrayList<>();

    /**
     * {@link #alerts} lock
     */
    private final Lock alertsLock  = new ReentrantLock();

    /**
     * Listener for {@link QueueNames#ALERT} queue
     * @param alertMessage Message that we want to show as alert
     */
    @RabbitListener(queues = QueueNames.ALERT)
    public void processAlertMessage(Alert alertMessage) {
        alertsLock.lock();
        try {
            alerts.add(alertMessage);
        } finally {
            alertsLock.unlock();
        }
    }


    @Override
    public List<Alert> getAvailableAlerts() {
        alertsLock.lock();
        try {
            ArrayList<Alert> alerts = new ArrayList<>(this.alerts);
            this.alerts.clear();
            return alerts;
        } finally {
            alertsLock.unlock();
        }
    }
}
