package otts.test.work.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import otts.test.work.util.ButtonColor;

import java.io.Serializable;

/**
 * Created by alex on 05.09.2015.<br/>
 * User clicks one of color button
 */
public class UserClickColorButton implements UserMessage, Serializable {

    /**
     * User id
     */
    private final int id;

    /**
     * Color of the clicked button
     */
    private final ButtonColor color;

    public UserClickColorButton(@JsonProperty("id") int id, @JsonProperty("color") ButtonColor color) {
        this.id = id;
        this.color = color;
    }

    /**
     * Gets Color of the clicked button.
     *
     * @return Value of Color of the clicked button.
     */
    public ButtonColor getColor() {
        return color;
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
        return "User " + id + " clicks " + color.getName().toLowerCase() + " button";
    }

    @Override
    public String toString() {
        return "UserClickColorButton{" +
                "id=" + id +
                ", color=" + color +
                '}';
    }
}
