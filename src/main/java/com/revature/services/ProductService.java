package com.revature.services;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata) {
    	return productRepository.saveAll(productList);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    // Product search
    public List<Product> findBySearch(String query){
        List<Product> allProducts = productRepository.findAll();

        List<Product> searchMatch = new ArrayList<Product>();
        if(query.length()==0) {
            return allProducts;
        }
        else{
            // Add by matching substring 
            for(int i = 0; i < allProducts.size(); i++){
                String prodName = allProducts.get(i).getName();
                String prodDesc = allProducts.get(i).getDescription();
                boolean nameMatch = false; boolean descMatch  = false;

                if(prodName!=null && prodName.toLowerCase().contains(query.toLowerCase()) ){
                    nameMatch = true;
                }
                if(query.length()>1){
                    if(prodDesc!=null && prodDesc.toLowerCase().contains(query.toLowerCase()) ){
                        descMatch = true;
                    }
                }
               
                if(nameMatch || descMatch) searchMatch.add(allProducts.get(i));
            }
        }
        

        return searchMatch;
    }
}
