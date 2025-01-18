package com.example.TwinleavesAssignment.Repository;

import com.example.TwinleavesAssignment.Models.GtinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GtinRepository extends JpaRepository<GtinModel, Long> {
    @Query(value = "select distinct g.id, g.gtin, g.product_product_id from gtin_model g inner join batch b on b.gtin_model_id = g.id where b.available_quantity > 0", nativeQuery = true)
    List<GtinModel> findGtinWithPositiveAvailableQuantity();
}
