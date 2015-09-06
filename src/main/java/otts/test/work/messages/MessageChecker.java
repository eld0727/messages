package otts.test.work.messages;

/**
 * Created by alex on 06.09.2015.<br/>
 * Checks message for some condition
 */
public interface MessageChecker {

    /**
     * Checks message for some condition
     * @param message message to check
     * @return is message applicable by this check
     */
    boolean check(UserMessage message);
}
