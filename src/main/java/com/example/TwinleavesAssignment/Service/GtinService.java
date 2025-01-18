package com.example.TwinleavesAssignment.Service;

import com.example.TwinleavesAssignment.Models.Batch;
import com.example.TwinleavesAssignment.Models.GtinModel;
import com.example.TwinleavesAssignment.Models.Product;
import com.example.TwinleavesAssignment.Repository.GtinRepository;
import com.example.TwinleavesAssignment.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GtinService {

    @Autowired
    private GtinRepository gtinRepository;

    @Autowired
    private ProductRepository productRepository;

    public String issueProduct(Long id, Long productId) throws Exception{
        Optional<GtinModel> optionalGtinModel = gtinRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalGtinModel.isEmpty()){
            throw new Exception("gtin_id is invalid!");
        }
        if(optionalProduct.isEmpty()){
            throw new Exception("productId is invalid!");
        }

        GtinModel gtinModel = optionalGtinModel.get();
        Product product = optionalProduct.get();

        product.setGtinModel(gtinModel);
        gtinModel.setProduct(product);

        productRepository.save(product);
        return "Product successfully issued to Gtin.";
    }

    public String addGtin(GtinModel gtinModel) throws Exception{
        if(gtinModel.getId() != null){
            throw new Exception("Gtin already exists.");
        }
        gtinRepository.save(gtinModel);
        return "Gtin has been successfully save into Database.";
    }

    public List<GtinModel> getGtin() throws Exception{
        List<GtinModel> gtinModelList = gtinRepository.findAll();

        if(gtinModelList.isEmpty()){
            throw new Exception("Gtin is Empty!");
        }
        return gtinModelList;
    }

    public List<GtinModel> positiveAvailableQuantity() throws Exception{
        List<GtinModel> filteredGtinModels = gtinRepository.findGtinWithPositiveAvailableQuantity();

        if(filteredGtinModels.isEmpty()){
            throw new Exception("Gtin is Empty with Positive Available Quantity!");
        }

        return filteredGtinModels;


        /* List<GtinModel> gtinModelList = gtinRepository.findAll();

        if(gtinModelList.isEmpty()){
            throw new Exception("Gtin is Empty with Positive Available Quantity!");
        }

        List<GtinModel> filteredGtinModels = new ArrayList<>();

        for(GtinModel gtinModel : gtinModelList){
            List<Batch> filteredBatches = new ArrayList<>();

            for(Batch batch : gtinModel.getBatchList()){
                if (batch.getAvailableQuantity() > 0) {
                    // System.out.println("Batch ID: " + batch.getBatchId() + ", Available Quantity: " + batch.getAvailableQuantity());
                    filteredBatches.add(batch);
                }
            }

            if(!filteredBatches.isEmpty()){
                gtinModel.setBatchList(filteredBatches);
                filteredGtinModels.add(gtinModel);
            }
        }

        return filteredGtinModels; */
    }
}
