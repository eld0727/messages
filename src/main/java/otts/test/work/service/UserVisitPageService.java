package otts.test.work.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otts.test.work.messages.MessageChecker;
import otts.test.work.messages.UserMessage;
import otts.test.work.messages.UserVisitsPage;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 06.09.2015.<br/>
 * Service providing listener for user visits page
 */
@Service
public class UserVisitPageService {

    @Autowired
    private MessagesStorageService messagesStorageService;

    /**
     * Listener for {@link QueueNames#USER_VISIT_PAGE} queue
     * @param userVisitsPage message
     */
    @RabbitListener(queues = QueueNames.USER_VISIT_PAGE)
    public void processAlertMessage(UserVisitsPage userVisitsPage) {
        messagesStorageService.checkExpectedMessages(new UserVisitMessageChecker(userVisitsPage.getId()));
    }

    /**
     * Checks {@link UserVisitsPage} messages of user with specified id
     */
    private class UserVisitMessageChecker implements MessageChecker {
        /**
         * User id to check
         */
        private final int id;

        private UserVisitMessageChecker(int id) {
            this.id = id;
        }

        @Override
        public boolean check(UserMessage message) {
            return message.getId() == id && message instanceof UserVisitsPage;
        }
    }
}
