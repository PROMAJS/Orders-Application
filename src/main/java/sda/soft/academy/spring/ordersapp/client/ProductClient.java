package sda.soft.academy.spring.ordersapp.client;

import sda.soft.academy.spring.ordersapp.dto.ExternalProductDto;

import java.util.List;

public interface ProductClient {

    public List<ExternalProductDto> findAll();

    public ExternalProductDto findById(Long id);
}
