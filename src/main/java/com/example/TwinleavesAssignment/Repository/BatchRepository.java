package com.example.TwinleavesAssignment.Repository;

import com.example.TwinleavesAssignment.Models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0 ORDER BY b.inwardedOn DESC")
    List<Batch> findBatchesWithNegativeOrZeroQuantityOrderByInwardedOn();
}
