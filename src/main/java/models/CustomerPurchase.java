package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "customer_widgets")
@Entity
public class CustomerPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "widget_id")
    private Widget widget;

    @Column(name = "quantity_bought")
    private Integer quantityBought;

    @Column(name = "purchase_date")
    private Instant purchaseDate;

    public CustomerPurchase() {
    }

    @Override
    public String toString() {
        return "CustomerPurchase{" +
                "id=" + id +
                ", customer=" + customer +
                ", widget=" + widget +
                ", quantityBought=" + quantityBought +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    // accessors
    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(Integer quantityBought) {
        this.quantityBought = quantityBought;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}