package com.view.market.bea.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.view.market.bea.model.StockRecord;

public interface RecordRepository extends MongoRepository<StockRecord, String> {
	
	@Query("{'stock' : ?0}")
	public List<StockRecord> findByStockName(String stockName);
}
