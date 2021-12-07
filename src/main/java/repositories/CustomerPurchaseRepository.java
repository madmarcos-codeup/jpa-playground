package repositories;

import models.Customer;
import models.CustomerPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerPurchaseRepository extends JpaRepository<CustomerPurchase, Integer> {

}