package models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne()
//    @JsonIgnoreProperties("purchases")
    @ToString.Exclude
    private Customer customer;

    @Column(name = "item_desc")
    private String itemDescription;

    @Column
    private Double purchaseAmount;

}