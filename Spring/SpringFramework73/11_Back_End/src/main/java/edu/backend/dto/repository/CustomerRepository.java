package edu.backend.dto.repository;

import edu.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//JpaRepository<Customer-customer table, String-customer table's primary key type  >
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
