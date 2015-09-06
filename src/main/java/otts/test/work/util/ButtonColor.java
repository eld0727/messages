package otts.test.work.util;

/**
 * Created by alex on 05.09.2015.<br/>
 * Button color
 */
public enum ButtonColor {
    Red("Red", "#FF0000"),
    Green("Green", "#00FF00"),
    Blue("Blue", "#0000FF"),
    Yellow("Yellow", "#FFFF00")
    ;
    /**
     * Color name
     */
    private final String name;

    /**
     * Style representation (#rr0000)
     */
    private final String color;

    ButtonColor(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Gets Color name.
     *
     * @return Value of Color name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Style representation.
     *
     * @return Value of Style representation.
     */
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ButtonColor{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
