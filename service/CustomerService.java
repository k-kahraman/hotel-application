package service;

import models.Customer;

import java.util.*;

/**
 * Customer service class. <br>
 * This class utilizes Collections to store, retrieve and process data. <br>
 * Also a <strong>singleton</strong> class. <br>
 */
public class CustomerService {

    /**
     * Static Reference and Singleton initialization
     */
    private static CustomerService customerService;

    private CustomerService(){}

    public static CustomerService getInstance() {
        if (customerService == null) { customerService = new CustomerService(); }
        return customerService;
    }

    /**
     * Hash Map to hold Mail (Key) and Customer (Value).
     */
    private final Map<String, Customer> customerMap = new HashMap<>();

    /**
     * Method to create a new Customer. Customer also put to <strong>Customer Map</strong>
     * @param userEmail Customer's mail address. It <strong>must</strong> be in <i>"name@domain.com"</i> format.
     * @param firstName Customer's first name. Ex. <i>Kaan</i>
     * @param lastName Customer's last name. Ex. <i>Kahraman</i>
     */
    public void addCustomer(String userEmail, String firstName, String lastName) {
        Customer customer = new Customer(userEmail, firstName, lastName);
        customerMap.put(userEmail, customer);
    }

    /**
     * Method to check customer map and returns if exist, otherwise returns null.
     * @param userEmail is the Key
     * @return Customer value or null
     */
    public Customer getCustomer(String userEmail) {
        if (customerMap.containsKey(userEmail))
            return customerMap.get(userEmail);
        return null;
    }

    /**
     * Method to return all Customers or null if none exist, should be used with an <strong>ArrayList</strong>
     * @return An ArrayList of Customers
     */
    public Collection<Customer> getAllCustomers() {
        if (customerMap.isEmpty()) {
            return null;
        }
        else {
            List<Customer> customerList = new ArrayList<>();
            for (String mail :
                    customerMap.keySet()) {
                customerList.add(customerMap.get(mail));
            }
            return customerList;
        }
    }

}
