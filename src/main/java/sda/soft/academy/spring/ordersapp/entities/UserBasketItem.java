package sda.soft.academy.spring.ordersapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserBasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

}
