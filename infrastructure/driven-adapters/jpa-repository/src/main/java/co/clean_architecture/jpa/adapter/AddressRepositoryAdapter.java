package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.entity.AddressEntity;
import co.clean_architecture.jpa.entity.CustomerEntity;
import co.clean_architecture.jpa.repository.AddressJpaRepository;
import co.clean_architecture.model.address.Address;
import co.clean_architecture.model.address.gateways.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryAdapter implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;

    @Override
    @Transactional
    public Address save(Address address) {
        return mapAddressEntityToAddress(
                addressJpaRepository.save(
                        mapAddressToAddressEntity(address)));
    }

    @Override
    public Address findById(Long id) {
        return addressJpaRepository
                .findById(id)
                .map(this::mapAddressEntityToAddress)
                .orElse(null);
    }

    @Override
    public List<Address> findAllByCustomerId(Long customerId) {
        return addressJpaRepository
                .findByCustomerId(customerId)
                .stream()
                .map(this::mapAddressEntityToAddress)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        addressJpaRepository.deleteById(id);
    }

    private Address mapAddressEntityToAddress(AddressEntity addressEntity) {
        return Address
            .builder()
            .id(addressEntity.getId())
            .customerId(addressEntity.getCustomer().getId())
            .city(addressEntity.getCity())
            .street(addressEntity.getStreet())
            .zipCode(addressEntity.getZipCode())
            .createdAt(addressEntity.getCreatedAt())
            .build();
    }

    private AddressEntity mapAddressToAddressEntity(Address address) {
        return AddressEntity
            .builder()
            .id(address.getId())
            .customer(new CustomerEntity(address.getCustomerId()))
            .city(address.getCity())
            .street(address.getStreet())
            .zipCode(address.getZipCode())
            .createdAt(address.getCreatedAt())
            .build();
    }
}
