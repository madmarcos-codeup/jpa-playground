package services;

import models.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import repositories.PurchaseRepository;
import repositories.CustomerRepository;
import repositories.MyMessageRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;
    private PurchaseRepository customerPurchaseRepository;
    private MyMessageRepository myMessageRepository;

    public CustomerController(CustomerRepository customerRepository, PurchaseRepository customerPurchaseRepository, MyMessageRepository myMessageRepository) {
        this.customerRepository = customerRepository;
        this.customerPurchaseRepository = customerPurchaseRepository;
        this.myMessageRepository = myMessageRepository;
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<Customer> fetchCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{customerId}")
    @ResponseBody
    public Optional<Customer> fetchCustomer(@PathVariable("customerId") int customerId) {
        return customerRepository.findById(customerId);
    }

    @PutMapping("/customers/customerId}")
    @ResponseBody
    public String updateWidgets(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
        // we don't expect the incoming json to have the widget's id, so we set it
        customer.setId(customerId);
        customerRepository.save(customer);
        return "ok";
    }

    @PostMapping("/customers")
    @ResponseBody
    public String addCustomer(@RequestBody Customer customer) {
        // save the widget
        customer = customerRepository.save(customer);
        return "ok";
    }

    @PostMapping("/customers/{customerId}/widgets")
    @ResponseBody
    @Transactional(rollbackFor = { SQLException.class })
    public String addCustomerPurchase(@PathVariable("customerId") int customerId, @RequestBody Purchase purchase) throws SQLException {
        // assume that purchase already has widget id set in the request data
        Customer customer = customerRepository.getById(customerId);
        purchase.setCustomer(customer);
        // save the purchase
        purchase = customerPurchaseRepository.save(purchase);

        // insert some kind of log like record
        // where we can play with the transaction succeeding and failing
        myMessageRepository.save(new MyMessage(null));
//        if(true)
//            throw new SQLException("howdy");

        return "ok";
    }

}
