package service;

import models.Customer;
import models.Reservation;
import models.Room;

import java.util.*;

/**
 * Reservation Service class, this class utilizes our Models.
 * Also a <strong>Singleton</strong> object.
 */
public class ReservationService {

    /**
     * Static Reference and Singleton initialization.
     */
    private static ReservationService reservationService;

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (reservationService == null) { reservationService = new ReservationService(); }
        return reservationService;
    }

    /**
     * Our Collections that hold necessary values in memory.
     */
    private Map<Customer, Set<Reservation>> customerCollectionHashMap = new HashMap<>();
    Map<Integer, Room> roomMap = new HashMap<>();
    Set<Reservation> reservationSet = new HashSet<>();

    /**
     * Puts a new room to room map. Room Number is key and Room is value
     * @param room will be used as Value and room.getRoomNumber() is key.
     */
    public void addRoom(Room room) {
        try {
            roomMap.put(room.getRoomNumber(), room);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Searches roomMap for the corresponding key. If can't find the key will return <strong>null</strong>
     * @param roomNumber Key
     * @return corresponding Room or null.
     */
    public Room getARoom(int roomNumber) {
        if (roomMap.containsKey(roomNumber))
            return roomMap.get(roomNumber);
        return null;
    }

    /**
     * A new reservation will be created and added to lists
     * @param customer object
     * @param room object
     * @param checkInDate date in format of "dd/MM/yyyy".
     * @param checkOutDate date in format of "dd/MM/yyyy".
     * @return new reservation or null if fails to do so.
     */
    public Reservation reserveARoom(Customer customer, Room room, Date checkInDate, Date checkOutDate) {
        try {
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            // Checking if reservation made again
            if (reservationSet.isEmpty()) {
                Set<Reservation> customersReservationsSet = new HashSet<>();
                customersReservationsSet.add(reservation);
                reservationSet.add(reservation);
                customerCollectionHashMap.put(customer, customersReservationsSet);
            } else {
                for (Reservation r :
                        reservationSet) {
                    if (r.getCheckInDate().equals(reservation.getCheckInDate()) || r.getCheckOutDate().equals(reservation.getCheckOutDate()))
                        return null;
                }
                Set<Reservation> customersReservationsSet = new HashSet<>();
                customersReservationsSet.add(reservation);
                reservationSet.add(reservation);
                customerCollectionHashMap.put(customer, customersReservationsSet);
            }
            return reservation;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Finds rooms between given dates
     * @param checkInDate date in format of "dd/MM/yyyy".
     * @param checkOutDate date in format of "dd/MM/yyyy".
     * @return new RoomList or null
     */
    public Collection<Room> findRooms(Date checkInDate, Date checkOutDate) {
        List<Room> roomList = new ArrayList<>();
        for (Reservation reservation :
                reservationSet) {
            if (reservation.getCheckInDate() == checkInDate && reservation.getCheckOutDate() == checkOutDate)
                roomList.add(reservation.getRoom());

        }
        return roomList.isEmpty() ? null : roomList;
    }

    /**
     * Method to get a customers all reservations.
     * @param customer is key in the Map that hold Customer and Reservations.
     * @return A <strong>Set</strong> of Reservations
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return customerCollectionHashMap.getOrDefault(customer, null);
    }

    /**
     * Prints all reservations. If no reservation made it'll show corresponding message.
     */
    public void printAllReservations() {
        if (reservationSet.isEmpty())
            System.out.println("There are no reservations.");
        else {
            for (Reservation reservation :
                    reservationSet) {
                System.out.println(reservation.toString());
            }
        }
    }

}
