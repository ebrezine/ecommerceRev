package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.repositories.ProductDetailsRepository;
import com.revature.models.ProductDetails;

import java.util.List;

@Service
public class ProductDetailsService {
//comment
    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsService(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    public List<ProductDetails> findAll() {
        return productDetailsRepository.findAll();
    }


}
