package otts.test.work.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by alex on 05.09.2015.<br/>
 * Message containing information which user visit page with messages.
 */

public class UserVisitPage implements UserMessage, Serializable{

    /**
     * user id
     */
    private final int id;

    public UserVisitPage(@JsonProperty("id") int id) {
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
        return "User " + id + " visit page" ;
    }

    @Override
    public String toString() {
        return "UserVisitPage{" +
                "id=" + id +
                '}';
    }
}
