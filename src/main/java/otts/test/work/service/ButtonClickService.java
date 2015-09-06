package otts.test.work.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otts.test.work.messages.MessageChecker;
import otts.test.work.messages.UserClickAnyButton;
import otts.test.work.messages.UserClickColorButton;
import otts.test.work.messages.UserMessage;
import otts.test.work.util.ButtonColor;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 06.09.2015.<br/>
 * Service providing listener for color button clicks
 */
@Service
public class ButtonClickService {

    @Autowired
    private MessagesStorageService messagesStorageService;

    /**
     * Listener for {@link QueueNames#BUTTON_CLICK} queue
     * @param buttonClick message
     */
    @RabbitListener(queues = QueueNames.BUTTON_CLICK)
    public void processAlertMessage(UserClickColorButton buttonClick) {
        messagesStorageService.checkExpectedMessages(new UserClickSomeButtonMessageChecker(
                buttonClick.getId(),
                buttonClick.getColor()
        ));
    }

    /**
     * Checks button click messages of user with specified id
     */
    private class UserClickSomeButtonMessageChecker implements MessageChecker {
        /**
         * User id to check
         */
        private final int id;

        /**
         * Clicked color
         */
        private final ButtonColor color;

        private UserClickSomeButtonMessageChecker(int id, ButtonColor color) {
            this.id = id;
            this.color = color;
        }

        @Override
        public boolean check(UserMessage message) {
            return message.getId() == id && (
                    message instanceof UserClickAnyButton ||
                    message instanceof UserClickColorButton && ((UserClickColorButton) message).getColor().equals(color)
            );
        }
    }
}
