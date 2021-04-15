package models;

import enums.RoomType;

/**
 * Our <i>concrete</i> Room class
 */
public class Room implements IRoom {

    private double cost;
    private int roomNumber;
    private RoomType roomType;
    /**
     * Room constructor
     * @param roomNumber <strong>Unique</strong> room number/id.
     * @param roomType It uses <strong>RoomType</strong> enum.
     * @param cost Cost of the room. It is in <strong>Double</strong>.
     *             If isFree is true regardless of given value it'll be <strong>set</strong> zero.
     * @see RoomType
     */
    public Room(int roomNumber, RoomType roomType, double cost) {
        this.cost = cost;
        if (cost < 0)
            throw new IllegalArgumentException("Cost can't be lower than zero.");
        this.roomNumber = roomNumber;
        if (roomNumber <= 0)
            throw new IllegalArgumentException("Room number must be positive");
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return String.format("Room number is %d\nRoom cost is $%.2f\nRoom type is %s", roomNumber, cost,roomType.getRoomName());
    }

    // GETTER AND SETTERS ###############################################################################
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return cost;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
