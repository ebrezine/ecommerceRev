package com.revature.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.ProductDetails;
import com.revature.services.ProductDetailsService;

@RestController
@RequestMapping("/api/product/productDetails")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://project3-frontend-static-website.s3-website.us-east-2.amazonaws.com"}, allowCredentials = "true")
public class ProductDetailsController {
    //comment
    private final ProductDetailsService productDetailsService;

    public ProductDetailsController(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    //@Authorized
    @GetMapping
    public ResponseEntity<List<ProductDetails>> getProductDetails() {
        return ResponseEntity.ok(productDetailsService.findAll());
    }


}
