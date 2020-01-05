package sda.soft.academy.spring.ordersapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ExternalProductDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private String type;
}
