package co.clean_architecture.usecase.address;

import co.clean_architecture.model.address.Address;
import co.clean_architecture.model.address.gateways.AddressRepository;
import co.clean_architecture.model.customer.Customer;
import co.clean_architecture.model.customer.gateways.CustomerRepository;
import co.clean_architecture.model.address.exception.AddressNotFoundException;
import co.clean_architecture.model.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class AddressUseCase {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public Address saveAddress(Address address) {
        validateCustomerExists(address.getCustomerId());
        address.setCreatedAt(LocalDate.now());
        return addressRepository.save(address);
    }

    public Address findAddressById(Long addressId) {
        return this.validateAddressExists(addressId);
    }

    public List<Address> findAddressesByCustomerId(Long customerId) {
        validateCustomerExists(customerId);
        return addressRepository.findAllByCustomerId(customerId);
    }

    public Address updateAddress(Address address) {
        Address existingAddress = validateAddressExists(address.getId());
        validateCustomerExists(existingAddress.getCustomerId());
        address.setCustomerId(existingAddress.getCustomerId());
        return addressRepository.save(address);
    }

    public void deleteAddressById(Long addressId) {
        validateAddressExists(addressId);
        addressRepository.deleteById(addressId);
    }

    private void validateCustomerExists(Long customerId) {
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
    }

    private Address validateAddressExists(Long addressId) {
        Address address = addressRepository.findById(addressId);
        if (address == null) {
            throw new AddressNotFoundException("Address with ID " + addressId + " not found.");
        }
        return address;
    }
}
