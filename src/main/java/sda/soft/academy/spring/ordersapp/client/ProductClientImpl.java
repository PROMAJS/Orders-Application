package sda.soft.academy.spring.ordersapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sda.soft.academy.spring.ordersapp.dto.ExternalProductDto;

import java.util.List;

@Component
public class ProductClientImpl implements ProductClient {

    @Value("${product.server.address}")
    private String productServerAddress;

    private RestTemplate restTemplate;

    @Autowired
    public ProductClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<ExternalProductDto> findAll() {
        ResponseEntity<List<ExternalProductDto>> response = restTemplate.exchange(
                productServerAddress+"/rest/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ExternalProductDto>>(){});
        return response.getBody();
    }

    @Override
    public ExternalProductDto findById(Long id) {
        return restTemplate.getForObject(productServerAddress+"/rest/products/"+id, ExternalProductDto.class);
    }
}
