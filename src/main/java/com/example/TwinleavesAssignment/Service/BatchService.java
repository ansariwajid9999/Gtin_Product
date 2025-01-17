package com.example.TwinleavesAssignment.Service;

import com.example.TwinleavesAssignment.Models.Batch;
import com.example.TwinleavesAssignment.Models.GtinModel;
import com.example.TwinleavesAssignment.Models.Product;
import com.example.TwinleavesAssignment.Repository.BatchRepository;
import com.example.TwinleavesAssignment.Repository.GtinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService{

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private GtinRepository gtinRepository;

    public String addBatch(Batch batch) throws Exception{
        if(batch.getBatchId() != null){
            throw new Exception("Batch already exists.");
        }
        batchRepository.save(batch);
        return "Batch has been successfully save into Database.";
    }

    public List<Product> allProductsWithBatch(Long batchId) throws Exception{
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if(optionalBatch.isEmpty()){
            throw new Exception("BatchId is invalid!");
        }

        Batch batch = optionalBatch.get();
        List<Product> productList = batch.getProductList();

        return productList;
    }

    public String issueProduct(Long id, Long batchId) throws Exception{
        Optional<GtinModel> optionalGtinModel = gtinRepository.findById(id);
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if(optionalGtinModel.isEmpty()){
            throw new Exception("gtin_id is invalid!");
        }
        if(optionalBatch.isEmpty()){
            throw new Exception("batchId is invalid!");
        }

        GtinModel gtinModel = optionalGtinModel.get();
        Batch batch = optionalBatch.get();

        batch.setGtinModel(gtinModel);
        gtinModel.getBatchList().add(batch);

        gtinRepository.save(gtinModel);
        return "batch successfully issued to Gtin.";
    }

    public Batch inwardedOn() throws Exception{
        List<Batch> batches = batchRepository.findBatchesWithNegativeOrZeroQuantityOrderByInwardedOn();
        if(batches.isEmpty()){
            throw new Exception("Latest batch not found!");
        }
        return batches.get(0);
    }
}
