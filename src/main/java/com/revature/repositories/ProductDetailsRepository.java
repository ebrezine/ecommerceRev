package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.revature.models.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer>{
    //comment
}
