package otts.test.work.service;

import java.util.List;

/**
 * Created by alex on 06.09.2015.<br/>
 * Storage for expected and finished messages
 */
public interface MessagesStorageService {

    /**
     * Store message that will be stored as expected
     * @param message    message to store
     */
    void storeMessage(Object message);

    /**
     * Gets all expected messages
     * @return all expected messages
     */
    List<Object> getExpectedMessages();

    /**
     * Gets all finished messages
     * @return all finished messages
     */
    List<Object> getFinishedMessages();
}
