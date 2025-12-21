package co.clean_architecture.api;

import co.clean_architecture.model.customer.Customer;
import co.clean_architecture.usecase.customer.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerRest {

    private final CustomerUseCase customerUseCase;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerUseCase.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerUseCase.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerUseCase.saveCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerUseCase.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerUseCase.updateCustomer(customer));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id, @RequestBody String status) {
        customerUseCase.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
