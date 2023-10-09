package com.hitruong.store.repository;

import com.hitruong.store.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findById(int id);
}