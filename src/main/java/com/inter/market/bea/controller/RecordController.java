package com.inter.market.bea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inter.market.bea.model.StockRecord;
import com.inter.market.bea.service.RecordService;

@Controller
@RequestMapping("/api/v1")
public class RecordController {
	
    @Autowired
    private RecordService recordService;
    
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			recordService.upload(file);
			return new ResponseEntity<>("Data Set saved to database successully", HttpStatus.OK); 
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data Set insert failed to database ", HttpStatus.INTERNAL_SERVER_ERROR); 
		}

	}

	@GetMapping("/stock/{stock}")
    public ResponseEntity<List<StockRecord>> getStockInfo(@PathVariable(value="stock") String stockname) {
		List<StockRecord> records = recordService.findRecordByStack(stockname);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
	
	@PostMapping("/create")
    public ResponseEntity<StockRecord> create(@RequestBody StockRecord record) {
		StockRecord createdRecord = recordService.save(record);
		
	    if (createdRecord == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return new ResponseEntity<>(createdRecord, HttpStatus.OK);
	    }
	    
    }
    
    @GetMapping("/id/{id}")
    public  ResponseEntity<StockRecord> read(@PathVariable String id) {
    	StockRecord record = recordService.findById(id);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

}
