package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Table(name = "customers")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "customer_name", length = 100)
    @NotNull
    private String customerName;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<CustomerPurchase> purchases;

    // accessors

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CustomerPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<CustomerPurchase> purchases) {
        this.purchases = purchases;
    }
}