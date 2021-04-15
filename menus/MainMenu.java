package menus;

import api.AdminResource;
import api.HotelResource;
import appmanager.AppManager;
import enums.InputType;
import models.Reservation;
import models.Room;

import java.util.*;

/**
 * Main Menu Class is responsible for all Main Menu Inputs and Functionality of Users.
 */
public class MainMenu {

    /**
     * Static reference
     */
    private static MainMenu mainMenu;

    private MainMenu() {}

    /**
     * Put Synchronized before it to prevent Race Condition
     * @return new instance of MainMenu Object
     */
    public static synchronized MainMenu getInstance() {
        if (mainMenu == null) { mainMenu = new MainMenu(); }
        return mainMenu;
    }

    private static HotelResource hotelResource = HotelResource.getInstance();
    private static AdminResource adminResource = AdminResource.getInstance();

    /**
     * Shows main menu.
     */
    public void showMainMenu() {
        System.out.println("<-- Main Menu -->");
        System.out.println("<---------------------------->");
        System.out.println("1. Find and reserve a room.");
        System.out.println("2. See my reservations.");
        System.out.println("3. Create an account");
        System.out.println("4. Admin Menu.");
        System.out.println("5. Exit");
        System.out.println("<---------------------------->");
    }

    /**
     * Takes main menu input to select an option.
     * @return gives a number to make a selection.
     */
    public int takeMainMenuInput() {
        try {
            return (int) AppManager.parseAndTakeUserInput(InputType.INTEGER, "Please enter a number to proceed.", "Invalid input. Please try again.");
        } catch (Exception ex) {
            return takeMainMenuInput();
        }
    }

    /**
     * Finds and Books a room
     * @return 1 if successfully booked a room otherwise -1.
     */
    public int findAndReserveARoom() {
        String userMail = (String) AppManager.parseAndTakeUserInput(InputType.MAIL, "Please enter mail address to see reservations", "Invalid mail address format");
        if (hotelResource.getCustomer(userMail) == null) {
            System.out.println("This mail address doesn't match any account. Going back to main menu.");
            return -1;
        } else {
            if (adminResource.getAllRooms() == null) {
                System.out.println("There are no rooms right now. Returning back to the main menu.");
                return -1;
            } else {
                System.out.println("Available rooms:");
                for (Room room :
                        adminResource.getAllRooms()) {
                    System.out.println(room.toString());
                }
                Date checkInDate = (Date) AppManager.parseAndTakeUserInput(InputType.DATE, "Please enter check in date. Ex. 26/03/1999", "Invalid date format input");
                Date checkOutDate = (Date) AppManager.parseAndTakeUserInput(InputType.DATE, "Please enter check in date. Ex. 29/03/1999", "Invalid date format input");
                int roomNumber = (int) AppManager.parseAndTakeUserInput(InputType.INTEGER, "Please enter room number you want to book.", "Invalid number.");
                try {
                    if (hotelResource.bookARoom(userMail, hotelResource.getARoom(roomNumber), checkInDate, checkOutDate) == null) {
                        System.out.println("This room is already reserved in given dates. Please try another room.");
                        return -1;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return -1;
                }
                System.out.println("Successfully book the room.");
                return 1;
            }
        }
    }

    /**
     * Prints users reservations. If an error occurs corresponding messages will be prompted.
     */
    public void seeReservations() {
        String customerMail = (String) AppManager.parseAndTakeUserInput(InputType.MAIL, "Please enter mail address to see reservations", "Invalid mail address format");
        if (hotelResource.getCustomer(customerMail) == null)
            System.out.println("This mail address doesn't match any account. Going back to main menu.");
        else {
            if (hotelResource.getCustomersReservation(customerMail) == null)
                System.out.println("No reservations to show");
            else {
                try {
                    for (Reservation reservation : hotelResource.getCustomersReservation(customerMail)) {
                        System.out.println(reservation.toString());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Creates an user account. If an error occurs prompts corresponding message
     * @return 1 if successfully created user -1 otherwise
     */
    public int createAnAccount() {
        try {
            String email = (String) AppManager.parseAndTakeUserInput(InputType.MAIL, "Please enter your mail address. Ex. \"name@domain.com\"",
                    "Invalid mail address. Please enter in correct format \"name@domain.com\"");
            if (hotelResource.getCustomer(email) == null) {
                String fullName = (String) AppManager.parseAndTakeUserInput(InputType.STRING, "Please enter your name. Ex. Kaan Kahraman.",
                        "Invalid format. Please enter your name.");
                int lastSpaceIndex = fullName.lastIndexOf(" ");
                if (lastSpaceIndex == -1)
                    throw new IllegalArgumentException("Only a single name. Please enter full name");
                String firstName = fullName.substring(0, lastSpaceIndex);
                String lastName = fullName.substring(lastSpaceIndex + 1);
                hotelResource.createACustomer(email, firstName, lastName);
                return 1;
            } else {
                System.out.println("There is already an account with that email address.");
                return -1;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return createAnAccount();
        }
    }

    /**
     * Closes all application.
     */
    public void exit() {
        System.out.println("Exiting the application!");
        AppManager.shutDownScanner();
        System.exit(0);
    }

}
