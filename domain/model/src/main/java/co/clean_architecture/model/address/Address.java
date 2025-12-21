package co.clean_architecture.model.address;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Address {

    private Long id;
    private Long customerId;
    private String city;
    private String street;
    private String zipCode;
    private LocalDate createdAt;

}
