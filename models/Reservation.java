package models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reservation Model.
 */
public class Reservation {

    Customer customer;
    Room room;
    Date checkInDate;
    Date checkOutDate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     *
     * @param customer object
     * @param room object
     * @param checkInDate "dd/MM/yyyy" format
     * @param checkOutDate "dd/MM/yyyy" format
     */
    public Reservation(Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        String info = String.format("Reservation details:\nCustomers info %s.\n" +
                "Rooms info %s.\nCheck-in date %s and Check-out date %s",
                customer.toString(), room.toString(), simpleDateFormat.format(checkInDate), simpleDateFormat.format(checkOutDate));
        return info;
    }

    // GETTERS AND SETTERS ############################################################################################
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
