package com.hitruong.store.controller;

import com.hitruong.store.model.Product;
import com.hitruong.store.model.Rating;
import com.hitruong.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(
            @RequestParam(value = "limit", required = false) Integer limit
    ){
        return productService.getAllProducts(limit);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        Rating rating = productService.getRatingById(id);
        product.setRating(rating);
        return product;
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getAllProductsByCategory(@PathVariable String category){
        return productService.getProductsByCategory(category);
    }
}
