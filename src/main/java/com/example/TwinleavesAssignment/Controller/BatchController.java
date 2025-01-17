package com.example.TwinleavesAssignment.Controller;

import com.example.TwinleavesAssignment.Models.Batch;
import com.example.TwinleavesAssignment.Models.Product;
import com.example.TwinleavesAssignment.Service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/addBatch")
    public ResponseEntity<String> addBatch(@RequestBody Batch batch){
        try{
            String result = batchService.addBatch(batch);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allProductsWithBatch")
    public ResponseEntity<List<Product>> allProductsWithBatch(@RequestParam("batchId") Long batchId){
        try{
            List<Product> result = batchService.allProductsWithBatch(batchId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/issueBatchToGtin")
    public ResponseEntity<String> issueBatchToGtin(@RequestParam("id") Long id, @RequestParam("batchId") Long batchId){
        try{
            String result = batchService.issueProduct(id, batchId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("inwardedOn")
    public ResponseEntity<Batch> inwardedOn(){
        try{
            Batch result = batchService.inwardedOn();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
