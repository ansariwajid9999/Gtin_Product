package com.example.TwinleavesAssignment.Repository;

import com.example.TwinleavesAssignment.Models.GtinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GtinRepository extends JpaRepository<GtinModel, Long> {

}
