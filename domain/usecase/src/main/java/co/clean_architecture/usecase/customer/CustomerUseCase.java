package co.clean_architecture.usecase.customer;

import co.clean_architecture.model.customer.Customer;
import co.clean_architecture.model.customer.gateways.CustomerRepository;
import co.clean_architecture.usecase.customer.exception.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CustomerUseCase {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    public Customer getCustomerById(Long id) {

        Customer customer = customerRepository.getCustomerById(id);
        if(customer == null){
            throw new EmailCustomerExistsException("Customer with id " + id + " does not exist.");
        }
        return customer;
    }

    public Customer saveCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findByEmail(customer.getEmail());

        if (existingCustomer != null) {
            throw new co.clean_architecture.model.customer.exception.CustomerNotFoundException("Customer with email " + customer.getEmail() + " already exists.");
        }

        customer.setCreatedAt(LocalDate.now());
        customer.setStatus(StatusCustomerEnum.INACTIVE.name());
        validateEmail(customer.getEmail());
        validatePhone(customer.getPhone());

        return customerRepository.saveCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomer(id);
    }

    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = this.getCustomerById(customer.getId());

        Customer existingCustomerEmail = customerRepository.findByEmail(customer.getEmail());

        if (existingCustomerEmail != null && !Objects.equals(existingCustomerEmail.getId(), customer.getId())) {
            throw new EmailCustomerExistsException("The email " + customer.getEmail() + " is already in use by another customer.");
        }

        customer.setCreatedAt(existingCustomer.getCreatedAt());
        customer.setStatus(existingCustomer.getStatus());
        validateEmail(customer.getEmail());
        validatePhone(customer.getPhone());

        return customerRepository.saveCustomer(customer);
    }

    public void updateStatus(Long id, String status) {
        validateStatus(status);
        customerRepository.updateStatus(id, status);
    }

    private void validateStatus(String status) {
        try {
            StatusCustomerEnum.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new StatusCustomerIsNotValidException(
                    "The status '" + status + "' is not valid. Allowed values: ACTIVE, INACTIVE"
            );
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailCustomerException("Email cannot be null or empty");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidEmailCustomerException("Invalid email format");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new InvalidPhoneCustomerException("Phone cannot be null or empty");
        }

        if (!phone.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new InvalidPhoneCustomerException("Invalid phone format");
        }
    }

}
