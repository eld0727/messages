package otts.test.work.messages;

/**
 * Created by alex on 06.09.2015.<br/>
 * Message that was produced by user actions
 */
public interface UserMessage {

    /**
     * Get User id
     *
     * @return Value of User id
     */
    int getId();

    /**
     * Format message for rendering on view
     * @return formatted message
     */
    String presentation();
}
