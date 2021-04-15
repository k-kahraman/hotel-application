package models;

import enums.RoomType;

/**
 * Room Interface
 */
public interface IRoom {

    public int getRoomNumber();

    public double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();

}
