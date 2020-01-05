package sda.soft.academy.spring.ordersapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class UserBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Date createDate;

    @OneToMany
    @JoinColumn(name = "user_basket_id")
    private List<UserBasketItem> userBasketItems;
}
