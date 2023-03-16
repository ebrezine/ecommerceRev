package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;

@SpringBootTest(classes = ProductController.class)
@WebAppConfiguration
public class productController_test {
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;
    protected ProductRepository productRepository;
  
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {
      
        return objectMapper.readValue(json, clazz);
    }


    @Test
    void shouldCreateProduct() throws Exception{
        Product product = new Product(1, 2, 2.50, "description", "image.png", "name", 1);
        mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated())
            .andDo(print());
    }
    
    @Test
    void shouldReturnProduct() throws Exception{
        int id = 1;
        Product product = new Product(1, 2, 2.50, "description", "image.png", "name", 1);

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        mockMvc.perform(get("/api/products/{id}", id)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void shouldReturnListOfProducts() throws Exception{
        List<Product> products = new ArrayList<>(
            Arrays.asList(new Product(1, 2, 2.50, "description1", "image1.png", "name1", 1),
            new Product(2, 2, 3.50, "description2", "image2.png", "name2", 2),
            new Product(3, 5, 4.50, "description3", "image3.png", "name3", 3))
        );
        Mockito.when(productRepository.findAll()).thenReturn(products);
        mockMvc.perform(get("/api/products")).andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(products.size()))
        .andDo(print());
    }

    @Test
    void shouldReturnModifiedProduct() throws Exception{
        Product product = new Product(1, 2, 2.50, "description", "image.png", "name", 1);
        productRepository.save(product);
        String description = "newDescription";
        product.setDescription(description);
        productRepository.save(product);
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
        mockMvc.perform(get("/api/products/{id}", 1)).andExpect(status().isOk())
        .andExpect(jsonPath("$.description").value(description));
    }

    @Test
    void shouldDeleteProduct() throws Exception{
        Product product = new Product(1, 2, 2.50, "description", "image.png", "name", 1);
        productRepository.save(product);
        productRepository.delete(product);
    }
}

