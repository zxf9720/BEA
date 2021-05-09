package com.view.market.bea.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.view.market.bea.model.StockRecord;
import com.view.market.bea.service.RecordService;

@Controller
@RequestMapping("/api/v1")
public class RecordController {
	
	Logger logger = LoggerFactory.getLogger(RecordController.class);
	
    @Autowired
    private RecordService recordService;
    
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			logger.info("upload endpoint");
			recordService.upload(file);
			return new ResponseEntity<>("Data Set saved to database successully", HttpStatus.OK); 
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data Set insert failed to database ", HttpStatus.INTERNAL_SERVER_ERROR); 
		}

	}

	@GetMapping("/stock/{stock}")
    public ResponseEntity<List<StockRecord>> getStockInfo(@PathVariable(value="stock") String stockname) {
		logger.info("get endpoint");
		List<StockRecord> records = recordService.findRecordByStack(stockname);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
	
	@PostMapping("/create")
    public ResponseEntity<StockRecord> create(@RequestBody StockRecord record) {
		logger.info("create endpoint");
		StockRecord createdRecord = recordService.save(record);
		
	    if (createdRecord == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return new ResponseEntity<>(createdRecord, HttpStatus.OK);
	    }
	    
    }
    
    @GetMapping("/id/{id}")
    public  ResponseEntity<StockRecord> read(@PathVariable String id) {
    	logger.info("read endpoint");
    	StockRecord record = recordService.findById(id);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

}
