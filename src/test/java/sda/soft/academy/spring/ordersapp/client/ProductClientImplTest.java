package sda.soft.academy.spring.ordersapp.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sda.soft.academy.spring.ordersapp.dto.ExternalProductDto;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductClientImplTest {

    @Autowired
    private ProductClient productClient;

    @Test
    public void shouldGetExternalProductById() {
        ExternalProductDto expectedProductDto = new ExternalProductDto(1l, "butter db", new BigDecimal("2.55"), "FOOD");
        ExternalProductDto externalProductDto = productClient.findById(1l);
        assertEquals(expectedProductDto, externalProductDto);
    }

    @Test
    public void shouldGetListOfExternalProducts() {
        assertEquals(3,productClient.findAll().size());
    }

}