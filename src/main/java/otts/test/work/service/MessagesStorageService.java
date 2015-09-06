package otts.test.work.service;

import otts.test.work.messages.MessageChecker;
import otts.test.work.messages.UserMessage;

import java.util.List;

/**
 * Created by alex on 06.09.2015.<br/>
 * Storage for expected and finished messages
 */
public interface MessagesStorageService {

    /**
     * Stores message that will be stored as expected
     * @param message    message to store
     */
    void storeMessage(UserMessage message);

    /**
     * Checks stored expected messages and moves some of them to finished messages store
     * @param messageChecker    checker that decides which messages must be moves to finished messages store
     */
    void checkExpectedMessages(MessageChecker messageChecker);

    /**
     * Gets all expected messages
     * @return all expected messages
     */
    List<UserMessage> getExpectedMessages();

    /**
     * Gets all finished messages
     * @return all finished messages
     */
    List<UserMessage> getFinishedMessages();
}
