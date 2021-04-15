package api;

import models.Customer;
import models.Reservation;
import models.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

/**
 * The HotelResource have little to no behavior contained inside the class.
 * And makes use of the Service classes to implement its methods.
 */
public class HotelResource {

    /**
     * Static reference and singleton initialization.
     */
    private static HotelResource hotelResource;

    private HotelResource(){}

    /**
     * Gets Instance of HotelSource object
     * @return instance of HotelSource
     */
    public static HotelResource getInstance() {
        if (hotelResource == null) { hotelResource = new HotelResource(); }
        return hotelResource;
    }

    private static CustomerService customerService = CustomerService.getInstance();
    private static ReservationService reservationService = ReservationService.getInstance();

    /**
     * Gets Customer with mail address. If Customer doesn't exist it'll return null.
     * @param email key for customer.
     * @return Customer or null.
     */
    public Customer getCustomer(String email) {
        if (customerService.getCustomer(email) == null)
            return null;
        return customerService.getCustomer(email);
    }

    /**
     * Creates new customer
     * @param email email address name@domain.com format.
     * @param firstName name of the customer. Ex. Kaan.
     * @param lastName last name of the customer. Ex. Kahraman.
     */
    public void createACustomer(String email, String firstName, String lastName) {
        try {
            customerService.addCustomer(email, firstName, lastName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Gets a room with room number. If room doesn't exist it'll return null
     * @param roomNumber as key
     * @return Room or null.
     */
    public Room getARoom(int roomNumber) {
        if (reservationService.getARoom(roomNumber) == null)
            return null;
        return reservationService.getARoom(roomNumber);
    }

    /**
     * As Customer, books a room with given date and room. In other words creates a reservation.
     * @param customerEmail Customers mail address name@domain.com format
     * @param room Room to be booked
     * @param checkInDate date in format of "dd/MM/yyyy".
     * @param checkOutDate date in format of "dd/MM/yyyy".
     * @return new Reservation or null.
     */
    public Reservation bookARoom(String customerEmail, Room room, Date checkInDate, Date checkOutDate) {
        try {
            return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Returns a list of customers reservations. If list is empty will return null.
     * @param customerEmail to check reservations
     * @return list of reservations or null.
     */
    public Collection<Reservation> getCustomersReservation(String customerEmail) {
        return reservationService.getCustomersReservation(customerService.getCustomer(customerEmail)) == null ? null : reservationService.getCustomersReservation(customerService.getCustomer(customerEmail));
    }

    /**
     * Finds a room between given dates. If none find will return null.
     * @param checkIn date in format of "dd/MM/yyyy".
     * @param checkOut date in format of "dd/MM/yyyy".
     * @return Room or null.
     */
    public Collection<Room> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut) == null ? null : reservationService.findRooms(checkIn, checkOut);
    }

}
