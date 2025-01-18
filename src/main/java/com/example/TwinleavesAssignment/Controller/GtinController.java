package com.example.TwinleavesAssignment.Controller;

import com.example.TwinleavesAssignment.Models.GtinModel;
import com.example.TwinleavesAssignment.Service.GtinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gtin")
public class GtinController {

    @Autowired
    private GtinService gtinService;

    @PostMapping("/addGtin")
    public ResponseEntity<String> addGtin(@RequestBody GtinModel gtinModel){
        try{
            String result = gtinService.addGtin(gtinModel);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/issueProduct")
    public ResponseEntity<String> issueProduct(@RequestParam("id") Long id , @RequestParam("productId") Long productId){
        try{
            String result = gtinService.issueProduct(id, productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getGtin")
    public ResponseEntity<List<GtinModel>> getGtin(){
        try{
            List<GtinModel> result = gtinService.getGtin();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/positiveAvailableQuantity")
    public ResponseEntity<List<GtinModel>> positiveAvailableQuantity(){
        try{
            List<GtinModel> result = gtinService.positiveAvailableQuantity();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
