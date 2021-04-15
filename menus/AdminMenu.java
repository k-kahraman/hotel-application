package menus;

import api.AdminResource;
import enums.InputType;
import enums.RoomType;
import models.Customer;
import models.FreeRoom;
import models.IRoom;
import models.Room;
import appmanager.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin Menu Class is responsible for all Admin Menu Inputs and Functionality of Admin.
 */
public class AdminMenu {

    private static AdminMenu adminMenu;

    private AdminMenu() {}

    public static AdminMenu getInstance() {
        if (adminMenu == null) { adminMenu = new AdminMenu(); }
        return adminMenu;
    }

    private static AdminResource adminResource = AdminResource.getInstance();

    /**
     * Prints admin menu UI
     */
    public void showAdminMenu() {
        System.out.println("<== ADMIN MENU ==>");
        System.out.println("<---------------------------->");
        System.out.println("1. See all Customers.");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("<---------------------------->");
    }

    /**
     * Takes Admin Menu input to select an option.
     * @return gives a number to make a selection.
     */
    public int takeAdminMenuInput() {
        try {
            return (int) AppManager.parseAndTakeUserInput(InputType.INTEGER, "Please enter a number to proceed", "Invalid input");
        } catch (Exception ex) {
            return takeAdminMenuInput();
        }
    }

    /**
     * Prints all users to command line. If an error occurs message will be prompted.
     */
    public void seeAllCustomers() {
        if (adminResource.getAllCustomers() == null) {
            System.out.println("No customer account to show.");
        } else {
            System.out.println("Showing all the customers");
            try {
                for (Customer customer :
                        adminResource.getAllCustomers()) {
                    System.out.println(customer);
                    System.out.println("\n");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Prints all rooms to command line. If an error occurs message will be prompted.
     */
    public void seeAllRooms() {
        if (adminResource.getAllRooms() == null) {
            System.out.println("There are no rooms. Add some!");
        } else {
            System.out.println("Showing all the rooms");
            try {
                for (Room room : adminResource.getAllRooms()) {
                    System.out.println(room);
                    System.out.println("\n");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Prints all reservations to command line. If an error occurs message will be prompted.
     */
    public void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    /**
     * Creates and adds a new room.
     * @return 1 if room successfully created -1 otherwise.
     */
    public int addARoom() {
        try {
            List<IRoom> roomList = new ArrayList<>();
            while (true) {
                boolean isFree = (boolean) AppManager.parseAndTakeUserInput(InputType.BOOLEAN, "Is that a free room. (N/y)", "Please enter N or Y.");
                if (isFree) {
                    int roomNumber = (int) AppManager.parseAndTakeUserInput(InputType.INTEGER, "Please enter room number.", "Invalid number.");
                    RoomType roomType = RoomType.getRoomType((String) AppManager.parseAndTakeUserInput(InputType.STRING, "Please enter room type. Single, Double, Triple, Quad, Queen, King.", "Invalid Room Type"));
                    roomList.add(new FreeRoom(roomNumber, roomType, 0));
                } else {
                    double cost = (double) AppManager.parseAndTakeUserInput(InputType.DOUBLE, "Please enter room cost.", "Invalid cost.");
                    int roomNumber = (int) AppManager.parseAndTakeUserInput(InputType.INTEGER, "Please enter room number.", "Invalid number.");
                    RoomType roomType = RoomType.getRoomType((String) AppManager.parseAndTakeUserInput(InputType.STRING, "Please enter room type. Single, Double, Triple, Quad, Queen, King.", "Invalid Room Type"));
                    roomList.add(new Room(roomNumber, roomType, cost));
                }
                if (!((boolean) AppManager.parseAndTakeUserInput(InputType.BOOLEAN, "Do you want to add another room? (Y/n).", "Invalid input.")))
                    break;
            }
            adminResource.addRoom(roomList);
            return 1;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

}


