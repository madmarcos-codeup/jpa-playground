package models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Set<Purchase> purchases;

    @ManyToOne
    @JoinColumn(name = "referrer_id")
    private Customer referrer;


    @OneToMany
    @JoinColumn(name = "referrer_id")
    private Set<Customer> referrals;

    // accessors

    @Override
    public String toString() {
        String output = "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", purchases= " + purchases +
                ", referred by= " + (this.getReferrer() == null ? null : this.getReferrer().getCustomerName()) +
                ", referrals= [";
        if(referrals != null) {
            for(Customer referral : this.referrals) {
                output += referral.getCustomerName() + ", ";
            }
        }
        return output + "] }";
    }

}