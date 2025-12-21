package co.clean_architecture.jpa.repository;

import co.clean_architecture.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByEmail(String email);

    @Modifying
    @Query("UPDATE CustomerEntity c SET c.status = :status WHERE c.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") String status);
}
