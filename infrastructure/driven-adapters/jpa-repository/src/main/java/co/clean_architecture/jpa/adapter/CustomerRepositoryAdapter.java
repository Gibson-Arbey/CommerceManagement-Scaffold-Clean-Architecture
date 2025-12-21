package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.entity.CustomerEntity;
import co.clean_architecture.jpa.repository.CustomerJpaRepository;
import co.clean_architecture.model.customer.Customer;
import co.clean_architecture.model.customer.gateways.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerJpaRepository.findAll()
                .stream()
                .map(this::mapCustomerEntityToCustomer)
                .toList();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerJpaRepository.findById(id)
                .map(this::mapCustomerEntityToCustomer)
                .orElse(null);
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return mapCustomerEntityToCustomer(
                customerJpaRepository.save(
                        mapCustomerToCustomerEntity(customer)));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerJpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, String status) {
        customerJpaRepository.updateStatusById(id, status);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerJpaRepository.findByEmail(email)
                .map(this::mapCustomerEntityToCustomer)
                .orElse(null);
    }

    private Customer mapCustomerEntityToCustomer(CustomerEntity customer) {
        return Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    private CustomerEntity mapCustomerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .createdAt(customer.getCreatedAt())
                .build();
    }

}
