package docrob.springjpatutorial;

import models.Customer;
import models.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import repositories.*;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Transactional(rollbackFor = { SQLException.class })
@Rollback(false)
class CustomerTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void fetchCustomers() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);
        assert true;
    }

    @Test
    void addCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("Customer 1");
        customerRepository.save(customer);
        assert true;
    }

    @Test
    void addPurchase() {
        Customer customer = customerRepository.getById(1);
        Purchase purchase = new Purchase();
        purchase.setPurchaseAmount(5.50);
        purchase.setItemDescription("Item 1");
        purchase.setCustomer(customer);
        purchaseRepository.save(purchase);
        fetchCustomers();
        assert true;
    }

}