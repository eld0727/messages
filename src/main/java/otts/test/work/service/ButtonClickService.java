package otts.test.work.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import otts.test.work.messages.UserClickButton;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 06.09.2015.<br/>
 * Service providing listener for color button clicks
 */
@Service
public class ButtonClickService {

    /**
     * Listener for {@link QueueNames#BUTTON_CLICK} queue
     * @param buttonClick message
     */
    @RabbitListener(queues = QueueNames.BUTTON_CLICK)
    public void processAlertMessage(UserClickButton buttonClick) {
        System.out.println(buttonClick);
    }
}
