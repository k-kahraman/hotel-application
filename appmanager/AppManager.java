package appmanager;

import enums.InputType;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages all app requirements. Like taking inputs and parsing them.
 * Also checks for mail RegEx.
 * And recommendation for reservations.
 */
public class AppManager {

    /**
     * Singleton
     */
    private static AppManager appManager;

    private AppManager() {}

    /**
     * Gets Instance of AppManager object
     * @return instance of AppManager
     */
    public static AppManager getInstance() {
        if (appManager == null) { appManager = new AppManager(); }
        return appManager;
    }

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Takes user input from command line.
     * @return Strings as input
     */
    public static String takeUserInput() {
        return scanner.nextLine();
    }

    /**
     * Parses and takes user input. Also prompts given message to the console or error if occurs.
     * When calling this function always be sure to <strong>type-cast</strong> to correct type.
     * @param inputType Input Type enum
     * @param inputMessage Message to be prompted
     * @param errorMessage Message to be prompted if an error occurs
     * @return parsed object type.
     * @see InputType
     */
    public static Object parseAndTakeUserInput(InputType inputType, String inputMessage, String errorMessage) {
        try {
            switch (inputType) {
                case INTEGER -> {
                    System.out.println(inputMessage);
                    return Integer.parseInt(takeUserInput());
                }
                case STRING -> {
                    System.out.println(inputMessage);
                    return takeUserInput();
                }
                case DOUBLE -> {
                    System.out.println(inputMessage);
                    return Double.parseDouble(takeUserInput());
                }
                case BOOLEAN -> {
                    System.out.println(inputMessage);
                    String userInput = takeUserInput();
                    if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n")) {
                        return userInput.equalsIgnoreCase("y");
                    } else {
                        throw new IllegalArgumentException(errorMessage);
                    }
                }
                case MAIL -> {
                    System.out.println(inputMessage);
                    String email = takeUserInput();
                    if (isMatchMail(email))
                        return email;
                    else
                        throw new IllegalArgumentException("Mail format is not correct");
                }
                case DATE -> {
                    System.out.println(inputMessage);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    return simpleDateFormat.parse(takeUserInput());
                }
                default -> {
                    System.out.println("Invalid arguments.");
                    return null;
                }
            }
        } catch (Exception ex) {
//            System.out.println(String.format("Expected %s but STRING is provided. " +
//                    "Please try again and provide a/an %s", inputType.toString(),  inputType.toString()));
            System.out.println(errorMessage);
            return parseAndTakeUserInput(inputType, inputMessage, errorMessage);
        }
    }

    /**
     * Checks if the given mail address's format is correct using RegEx. Returns <strong>true</strong> if RegEx matches otherwise <strong>false</strong>.
     * RegEx is taken from <a href="regexr.com">regexr.com</a>.
     * @param mail to be checked.
     * @return <strong>true</strong> if mail address format matches otherwise <strong>false</strong>.
     */
    private static boolean isMatchMail(String mail) {
        final String emailRegex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    /**
     * Closes scanner to prevent memory leaks.
     */
    public static void shutDownScanner() {
        scanner.close();
    }
}
