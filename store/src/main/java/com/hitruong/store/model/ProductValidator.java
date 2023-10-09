package com.hitruong.store.model;

import org.thymeleaf.util.StringUtils;

import java.util.Optional;

public class ProductValidator {

    public boolean isVAlid(Product product){
        return Optional.ofNullable(product)
                .filter(p -> StringUtils.isEmpty(p.getTitle()))
                .filter(p -> StringUtils.isEmpty(p.getDescription()))
                .filter(p -> p.getPrice() < 0)
                .isPresent();
    }
}
