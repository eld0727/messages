package otts.test.work.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import otts.test.work.messages.UserVisit;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 06.09.2015.<br/>
 * Service providing listener for user visit page
 */
@Service
public class UserVisitPageService {

    /**
     * Listener for {@link QueueNames#USER_VISIT_PAGE} queue
     * @param userVisit message
     */
    @RabbitListener(queues = QueueNames.USER_VISIT_PAGE)
    public void processAlertMessage(UserVisit userVisit) {
        System.out.println(userVisit);
    }
}
