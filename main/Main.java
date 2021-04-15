package main;

import menus.*;

/**
 * Hotel Application
 * @author Kaan Kahraman.
 */
public class Main {

    private static MainMenu mainMenu = MainMenu.getInstance();
    private static AdminMenu adminMenu = AdminMenu.getInstance();

    /**
     * Applications execution point.
     * @param args
     */
    public static void main(String[] args) {
        hotelApplication();
    }

    /**
     * Show/Print and functionalities like only showing static things to User
     * should not return anything and should be called here directly.
     * Functions that do any business things (like reading from user, adding anything to your
     * collections or modifying them) should be called here directly but they has to return some value.
     * After calling these functions here in this
     * loop you should check for the return value and do the next action accordingly.
     */
    private static void hotelApplication() {
        while (true) {
            mainMenu.showMainMenu();
            int uiInput = mainMenu.takeMainMenuInput();

            switch (uiInput) {
                case 1:
                    if (mainMenu.findAndReserveARoom() == 1)
                        break;
                    else
                        continue;
                case 2:
                    mainMenu.seeReservations();
                    continue;
                case 3:
                    if (mainMenu.createAnAccount() == 1) {
                        System.out.println("Successfully created user.");
                        break;
                    }
                    else {
                        System.out.println("Couldn't create user. Returning back to Main Menu.");
                        continue;
                    }
                case 4:
                    while (true) {
                        adminMenu.showAdminMenu();
                        int adminInput = adminMenu.takeAdminMenuInput();
                        switch (adminInput) {
                            case 1:
                                adminMenu.seeAllCustomers();
                                continue;
                            case 2:
                                adminMenu.seeAllRooms();
                                continue;
                            case 3:
                                adminMenu.seeAllReservations();
                                continue;
                            case 4:
                                adminMenu.addARoom();
                                continue;
                            case 5:
                                System.out.println("Going back to the Main Menu.");
                                break;
                            default:
                                System.out.println("Invalid input! Please enter again.");
                                continue;
                        }
                        break;
                    }
                    break;
                case 5:
                    mainMenu.exit();
                    break;
                default:
                    System.out.println("Invalid number.");
                    break;
            }
        }
    }

}
