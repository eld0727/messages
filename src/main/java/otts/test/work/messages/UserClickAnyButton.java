package otts.test.work.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by alex on 06.09.2015.<br/>
 * User clicks any button
 */
public class UserClickAnyButton implements UserMessage, Serializable {

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
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String presentation() {
        return "User " + id + " clicks any button";
    }
}
