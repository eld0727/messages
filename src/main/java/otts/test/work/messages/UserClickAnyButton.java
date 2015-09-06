package otts.test.work.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alex on 06.09.2015.<br/>
 * User clicks any button
 */
public class UserClickAnyButton {

    /**
     * User id
     */
    private final int id;

    public UserClickAnyButton(@JsonProperty("id") int id) {
        this.id = id;
    }

    /**
     * Gets User id.
     *
     * @return Value of User id.
     */
    public int getId() {
        return id;
    }
}
