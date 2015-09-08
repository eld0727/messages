package otts.test.work.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by alex on 05.09.2015.<br/>
 * Message containing information which user visits page with messages.
 */

public class UserVisitsPage implements UserMessage, Serializable{

    /**
     * user id
     */
    private final int id;

    public UserVisitsPage(@JsonProperty("id") int id) {
        this.id = id;
    }


    /**
     * Gets user id.
     *
     * @return Value of user id.
     */
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String presentation() {
        return "User " + id + " visits page" ;
    }

    @Override
    public String toString() {
        return "UserVisitsPage{" +
                "id=" + id +
                '}';
    }
}
