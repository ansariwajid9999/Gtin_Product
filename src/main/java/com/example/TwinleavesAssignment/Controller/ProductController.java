package com.example.TwinleavesAssignment.Controller;

import com.example.TwinleavesAssignment.Models.Product;
import com.example.TwinleavesAssignment.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        try{
            String result = productService.addProduct(product);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("issueBatch")
    public ResponseEntity<String> issueBatch(@RequestParam("batchId") Long batchId, @RequestParam("productId") Long productId){
        try{
            String result = productService.issueProduct(batchId, productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
