package enums;

/**
 * Room Type will be used in our Room Model and its siblings.
 * @see models.Room
 */
public enum RoomType {
    /**
     * Single: A room assigned to one person. May have one or more beds.
     */
    SINGLE  ("Single"),
    /**
     * Double: A room assigned to two people. May have one or more beds.
     */
    DOUBLE  ("Double"),
    /**
     * Triple: A room assigned to three people. May have two or more beds.
     */
    TRIPLE  ("Triple"),
    /**
     * Quad: A room assigned to four people. May have two or more beds.
     */
    QUAD    ("Quad"),
    /**
     * Queen: A room with a queen-sized bed. May be occupied by one or more people.
     */
    QUEEN   ("Queen"),
    /**
     * King: A room with a king-sized bed. May be occupied by one or more people.
     */
    KING    ("King");

    /**
     * This parameter will be used in getRoomType method
     */
    private final String roomName;
    RoomType(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Room Name getter
     * @return Room's name
     */
    public String getRoomName() { return roomName; }

    /**
     * Parses given string into RoomType
     * @param input string to be parsed
     * @return Parsed RoomType
     */
    public static RoomType getRoomType(String input) {
        for (RoomType r : values()) {
            if (r.roomName.equals(input)) {
                return r;
            }
        }
        return null;
    }

}
