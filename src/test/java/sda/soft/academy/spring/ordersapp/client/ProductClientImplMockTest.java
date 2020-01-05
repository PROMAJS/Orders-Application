package sda.soft.academy.spring.ordersapp.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import sda.soft.academy.spring.ordersapp.dto.ExternalProductDto;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@RestClientTest(ProductClientImpl.class)
public class ProductClientImplMockTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ProductClient productClient;

    @Test
    public void shouldGetExternalProduct() {
        ExternalProductDto expectedProductDto = new ExternalProductDto(1l, "butter db 2", new BigDecimal("3.55"), "FOOD");

        this.server.expect(MockRestRequestMatchers.requestTo("/rest/products/1"))
                .andRespond(MockRestResponseCreators.withSuccess("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"butter db 2\",\n" +
                        "  \"price\": 3.55,\n" +
                        "  \"type\": \"FOOD\"\n" +
                        "}", MediaType.APPLICATION_JSON));

        ExternalProductDto productDto=productClient.findById(1l);
        assertEquals(expectedProductDto, productDto);
    }
}