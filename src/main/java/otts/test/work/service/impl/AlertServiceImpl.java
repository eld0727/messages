package otts.test.work.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import otts.test.work.messages.Alert;
import otts.test.work.service.AlertService;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 05.09.2015.<br/>
 * Implementation for {@link AlertService} uses RabbitMQ alert query
 */
@Service
public class AlertServiceImpl implements AlertService {

    /**
     * Listener for {@link QueueNames#ALERT} queue
     * @param alertMessage Message that we want to show as alert
     */
    @RabbitListener(queues = QueueNames.ALERT)
    public void processAlertMessage(Alert alertMessage) {
        System.out.println(alertMessage);
    }
}
