package api;

import models.Customer;
import models.IRoom;
import models.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * API Part
 * AdminResources will be used in AdminMenu.
 */
public class AdminResource {

    /**
     * Singleton initialization
     */
    private static AdminResource adminResource;
    
    private AdminResource() {}

    public static AdminResource getInstance() {
        if (adminResource == null) { adminResource = new AdminResource(); }
        return adminResource;
    }

    /**
     * Required modules and collections.
     */
    private static CustomerService customerService = CustomerService.getInstance();
    private static ReservationService reservationService = ReservationService.getInstance();
    private static List<Integer> roomKeys = new ArrayList<>();

    /**
     * Searches for customer with given mail. If none find will return null.
     * @param email to search for the customer.
     * @return Customer or null
     */
    public Customer getCustomer(String email) {
        if (customerService.getCustomer(email) == null)
            return null;
        return customerService.getCustomer(email);
    }

    /**
     * Adds a new room. Can add several rooms at once.
     * @param rooms List of rooms to be added.
     */
    public void addRoom(List<IRoom> rooms) {
        try {
            for (IRoom room :
                    rooms) {
                roomKeys.add(room.getRoomNumber());
                reservationService.addRoom((Room) room);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Gets all the room. If no room is found will return null.
     * @return List of Rooms or null
     */
    public Collection<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        for (int roomNumber : roomKeys) {
            rooms.add(reservationService.getARoom(roomNumber));
        }
        return rooms.isEmpty() ? null : rooms;
    }

    /**
     * Gets all customers from Customer Service. If no customer exist returns null.
     * @return List of customers or null
     */
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers() == null ? null : customerService.getAllCustomers();
    }

    /**
     * Displays all reservations. Nothing will be shown if no reservation is made.
     */
    public void displayAllReservations() {
        reservationService.printAllReservations();
    }
    
}
