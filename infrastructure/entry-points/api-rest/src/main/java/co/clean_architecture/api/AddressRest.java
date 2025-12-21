package co.clean_architecture.api;

import co.clean_architecture.model.address.Address;
import co.clean_architecture.usecase.address.AddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressRest {

    private final AddressUseCase addressUseCase;

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        return ResponseEntity.ok(addressUseCase.saveAddress(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id){
        return ResponseEntity.ok(addressUseCase.findAddressById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Address>> getAddressesByCustomerId(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(addressUseCase.findAddressesByCustomerId(customerId));
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestBody Address address){
        return ResponseEntity.ok(addressUseCase.updateAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id){
        addressUseCase.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }


}
