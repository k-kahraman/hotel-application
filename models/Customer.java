package models;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This the concrete <i>Customer</i> class<br>
 * @see <a href="https://regexr.com/">RegExr</a> for the Email Regex<br>
 */
public class Customer {

    private String userEmail;
    private String firstName;
    private String lastName;

    /**
     * Customer constructor.
     * @param userEmail Customer's mail address. It <strong>must</strong> be in <i>"name@domain.com"</i> format! It'll pass through our <i>RegEx</i>.
     * @param firstName Customer's first name. Ex. <i>Kaan</i>
     * @param lastName Customer's last name. Ex. <i>Kahraman</i>
     */
    public Customer(String userEmail, String firstName, String lastName) {
        final String emailRegex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(userEmail);
        if (matcher.matches()) {
            this.userEmail = userEmail;
        } else {
            throw new IllegalArgumentException("User mail must be in format \"name@domain.com\"");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return userEmail.equals(customer.userEmail) && firstName.equals(customer.firstName) && lastName.equals(customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, firstName, lastName);
    }

    /**
     * @return formatted string to be used in outputs
     */
    @Override
    public String toString() {
        String info = String.format("Customer name %s %s.\nCustomer mail address %s.", firstName, lastName, userEmail);
        return info;
    }
}
