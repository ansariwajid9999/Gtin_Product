package com.example.TwinleavesAssignment.Service;

import com.example.TwinleavesAssignment.Models.Batch;
import com.example.TwinleavesAssignment.Models.GtinModel;
import com.example.TwinleavesAssignment.Models.Product;
import com.example.TwinleavesAssignment.Repository.BatchRepository;
import com.example.TwinleavesAssignment.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchRepository batchRepository;

    public String addProduct(Product product) throws Exception{
        if(product.getProductId() != null){
            throw new Exception("Product already exists.");
        }
        productRepository.save(product);
        return "Product has been successfully save into Database.";
    }

    public String issueProduct(Long batchId, Long productId) throws Exception{
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalBatch.isEmpty()){
            throw new Exception("gtin_id is invalid!");
        }
        if(optionalProduct.isEmpty()){
            throw new Exception("productId is invalid!");
        }

        Batch batch = optionalBatch.get();
        Product product = optionalProduct.get();

        product.setBatch(batch);
        batch.getProductList().add(product);

        batchRepository.save(batch);
        return "Product successfully issued to Batch.";
    }
}
