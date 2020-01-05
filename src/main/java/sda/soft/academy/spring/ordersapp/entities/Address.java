package sda.soft.academy.spring.ordersapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String street;

    @Column(length = 50)
    private String number;

    @Column(length = 10)
    private String postal;

    @Column(length = 100)
    private String city;
}
