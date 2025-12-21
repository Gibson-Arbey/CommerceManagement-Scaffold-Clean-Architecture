package co.clean_architecture.model.product.criteria;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class ProductCriteria {

    private String name;
    private Boolean appliesCategoryFilter;
    private List<Long> categoryIds;
    private Double maxPrice;
    private Double minPrice;
    private String status;

    public void setValuesIfNull() {
        if(Objects.equals(this.name, "")) {
            this.name = null;
        }

        this.appliesCategoryFilter = !this.categoryIds.isEmpty();

        if(Objects.equals(this.status, "")) {
            this.status = null;
        }

        if(this.maxPrice == null) {
            this.maxPrice =  Double.MAX_VALUE;
        }

        if(this.minPrice == null) {
            this.minPrice =  0.0;
        }
    }
}
