package co.clean_architecture.model.address.gateways;

import co.clean_architecture.model.address.Address;

import java.util.List;

public interface AddressRepository {

    Address save(Address address);

    Address findById(Long id);

    List<Address> findAllByCustomerId(Long customerId);

    void deleteById(Long id);
}
