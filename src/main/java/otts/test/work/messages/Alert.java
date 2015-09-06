package otts.test.work.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alex on 05.09.2015.<br/>
 * Alert message
 */
public class Alert {

    /**
     * Message that will be shown in alert window
     */
    private final String message;

    public Alert(@JsonProperty("message") String message) {
        this.message = message;
    }

    /**
     * Gets Message that will be shown in alert window.
     *
     * @return Value of Message that will be shown in alert window.
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "message='" + message + '\'' +
                '}';
    }
}
