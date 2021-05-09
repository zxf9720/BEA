package com.inter.market.bea.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.inter.market.bea.model.StockRecord;

public interface RecordService {

	public void upload(MultipartFile file) throws Exception;

	public List<StockRecord> findRecordByStack(String stack);

	public StockRecord save(StockRecord record);

	public StockRecord findById(String id);

}
