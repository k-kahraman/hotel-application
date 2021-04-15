package enums;

/**
 * Enum types for input
 */
public enum InputType {
    /**
     * Integer Input
     */
    INTEGER,
    /**
     * String Input
     */
    STRING,
    /**
     * Double Input
     */
    DOUBLE,
    /**
     * Boolean Input will be taken in Y/N format.
     */
    BOOLEAN,
    /**
     * Mail Input will be passed to isMailMatch method
     * @see appmanager.AppManager for the method
     */
    MAIL,
    /**
     * Date Input in format of "dd/MM/yyyy"
     */
    DATE
}
