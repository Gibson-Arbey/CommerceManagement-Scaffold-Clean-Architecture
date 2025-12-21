package co.clean_architecture.model.customer.gateways;

import co.clean_architecture.model.customer.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getCustomers();

    Customer getCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    void updateStatus(Long id, String status);

    Customer findByEmail(String email);
}
