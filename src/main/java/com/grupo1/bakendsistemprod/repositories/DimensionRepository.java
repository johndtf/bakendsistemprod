package com.grupo1.bakendsistemprod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo1.bakendsistemprod.entities.Dimension;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Short> {
    
    Dimension findByDimension(String dimension);
    
    
}
