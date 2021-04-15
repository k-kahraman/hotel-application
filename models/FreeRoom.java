package models;

import enums.RoomType;

/**
 * Free Room concrete class
 */
public class FreeRoom extends Room {

    int roomNumber;
    RoomType roomType;

    /**
     * Free Room Constructor
     * @param roomNumber integer represents room number.
     * @param roomType Room Type enumerators
     * @param cost set to 0 because room is free.
     * @see RoomType
     */
    public FreeRoom(int roomNumber, RoomType roomType, double cost) {
        super(roomNumber, roomType, 0);
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }


    @Override
    public String toString() {
        return String.format("Room number is %d\nRoom cost is FREE\nRoom type is %s", roomNumber, roomType.getRoomName());
    }
}
