package com.hitruong.store.service;

import com.hitruong.store.model.Product;
import com.hitruong.store.model.Rating;
import com.hitruong.store.repository.ProductRepository;
import com.hitruong.store.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RatingRepository ratingRepository;


    public List<Product> getAllProducts(Integer limit){
//        return Optional.ofNullable(limit)
//                .map(value -> productRepository.findAll(PageRequest.of(0, value)).getContent())
//                .orElseGet(() -> productRepository.findAll());

        List<Product> products;
        if(limit == null){
            products = productRepository.findAll();
        }else {
            products = productRepository.findAll(PageRequest.of(0, limit)).getContent();
        }

        for(Product p: products){
            Rating rating = ratingRepository.findById(p.getId());
            p.setRating(rating);
        }

        return products;
    }

    public Product getProductById(int id){
        return productRepository.findById(id);
    }

    public Rating getRatingById(int id){
        return ratingRepository.findById(id);
    }

    public List<String> getAllCategories(){
        List<String> categories = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (!categories.contains(p.getCategory())) {
                categories.add(p.getCategory());
            }
        }
        return categories;
    }

    public List<Product> getProductsByCategory(String category){
        List<Product> products = getAllProducts(null);
        List<Product> productsByCategory = new ArrayList<>();
        for(Product p: products){
            if(p.getCategory().equals(category)){
                productsByCategory.add(p);
            }
        }
        return productsByCategory;
    }

}
