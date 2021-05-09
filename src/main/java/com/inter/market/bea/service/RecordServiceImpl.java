package com.inter.market.bea.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inter.market.bea.model.StockRecord;
import com.inter.market.bea.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Override
	public void upload(MultipartFile file) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		CSVParser csvParser = null;

		try {
			InputStream uploadFile = file.getInputStream();
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(uploadFile, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			List<StockRecord> records = new ArrayList<StockRecord>();
			for (CSVRecord csvRecord : csvRecords) {
				StockRecord record = new StockRecord();
				if (!csvRecord.get("quarter").isEmpty())
					record.setQuarter(Integer.parseInt(csvRecord.get("quarter")));

				record.setStock(csvRecord.get("stock"));

				if (!csvRecord.get("date").isEmpty())
					record.setDate(formatter.parse(csvRecord.get("date")));
				if (!csvRecord.get("open").isEmpty())
					record.setOpen(new BigDecimal(removeDollarSign(csvRecord.get("open"))));
				if (!csvRecord.get("high").isEmpty())
					record.setHigh(new BigDecimal(removeDollarSign(csvRecord.get("high"))));
				if (!csvRecord.get("low").isEmpty())
					record.setLow(new BigDecimal(removeDollarSign(csvRecord.get("low"))));
				if (!csvRecord.get("close").isEmpty())
					record.setClose(new BigDecimal(removeDollarSign(csvRecord.get("close"))));
				if (!csvRecord.get("volume").isEmpty())
					record.setVolume(Long.valueOf(csvRecord.get("volume")));
				if (!csvRecord.get("percent_change_price").isEmpty())
					record.setPriceChangePercent(Double.valueOf(csvRecord.get("percent_change_price")));
				if (!csvRecord.get("percent_change_volume_over_last_wk").isEmpty())
					record.setVolumeChangeOverLastWeek(
							Double.valueOf(csvRecord.get("percent_change_volume_over_last_wk")));
				if (!csvRecord.get("previous_weeks_volume").isEmpty())
					record.setPreviousWeekVolume(Long.valueOf(csvRecord.get("previous_weeks_volume")));
				if (!csvRecord.get("next_weeks_open").isEmpty())
					record.setNextWeeksOpen(new BigDecimal(removeDollarSign(csvRecord.get("next_weeks_open"))));
				if (!csvRecord.get("next_weeks_close").isEmpty())
					record.setNextWeeksClose(new BigDecimal(removeDollarSign(csvRecord.get("next_weeks_close"))));
				if (!csvRecord.get("percent_change_next_weeks_price").isEmpty())
					record.setPercentChangNextWeeksPrice(
							Double.valueOf(csvRecord.get("percent_change_next_weeks_price")));
				if (!csvRecord.get("days_to_next_dividend").isEmpty())
					record.setDaysToNextDividend(Integer.parseInt(csvRecord.get("days_to_next_dividend")));
				if (!csvRecord.get("percent_return_next_dividend").isEmpty())
					record.setPercentReturnNextDividend(Double.valueOf(csvRecord.get("percent_return_next_dividend")));
				records.add(record);
			}

			recordRepository.saveAll(records);


		} catch (IOException | ParseException e) {
			e.printStackTrace();
			throw new Exception("Insert Error");
		} finally{
			csvParser.close();
		}
	}

	@Override
	public List<StockRecord> findRecordByStack(String stack) {
		return recordRepository.findByStockName(stack);
	}

	@Override
	public StockRecord save(StockRecord record) {
		StockRecord result = recordRepository.save(record);
		return result;
	}

	@Override
	public StockRecord findById(String id) {
		Optional<StockRecord> result = recordRepository.findById(id);
		return result.get();
	}

	private String removeDollarSign(String dollarValue) {
		return dollarValue.replace("$", "");
	}

}
