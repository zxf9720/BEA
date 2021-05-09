package com.inter.market.bea.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StockRecord")
public class StockRecord {

	@Id
	private String id;
	
	private int quarter;
	
	private String stock;
	
	private Date date;
	
	private BigDecimal open;
	
	private BigDecimal high;
	
	private BigDecimal low;
	
	private BigDecimal close;
	
	private long volume;
	
	private double priceChangePercent;
	
	private double volumeChangeOverLastWeek;
	
	private long previousWeekVolume;
	
	private BigDecimal nextWeeksOpen;
	
	private BigDecimal nextWeeksClose;
	
	private double percentChangNextWeeksPrice;
	
	private int daysToNextDividend;
	
	private double percentReturnNextDividend;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getPriceChangePercent() {
		return priceChangePercent;
	}

	public void setPriceChangePercent(double priceChangePercent) {
		this.priceChangePercent = priceChangePercent;
	}

	public double getVolumeChangeOverLastWeek() {
		return volumeChangeOverLastWeek;
	}

	public void setVolumeChangeOverLastWeek(double volumeChangeOverLastWeek) {
		this.volumeChangeOverLastWeek = volumeChangeOverLastWeek;
	}

	public long getPreviousWeekVolume() {
		return previousWeekVolume;
	}

	public void setPreviousWeekVolume(long previousWeekVolume) {
		this.previousWeekVolume = previousWeekVolume;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public BigDecimal getNextWeeksOpen() {
		return nextWeeksOpen;
	}

	public void setNextWeeksOpen(BigDecimal nextWeeksOpen) {
		this.nextWeeksOpen = nextWeeksOpen;
	}

	public BigDecimal getNextWeeksClose() {
		return nextWeeksClose;
	}

	public void setNextWeeksClose(BigDecimal nextWeeksClose) {
		this.nextWeeksClose = nextWeeksClose;
	}

	public double getPercentChangNextWeeksPrice() {
		return percentChangNextWeeksPrice;
	}

	public void setPercentChangNextWeeksPrice(double percentChangNextWeeksPrice) {
		this.percentChangNextWeeksPrice = percentChangNextWeeksPrice;
	}

	public int getDaysToNextDividend() {
		return daysToNextDividend;
	}

	public void setDaysToNextDividend(int daysToNextDividend) {
		this.daysToNextDividend = daysToNextDividend;
	}

	public double getPercentReturnNextDividend() {
		return percentReturnNextDividend;
	}

	public void setPercentReturnNextDividend(double percentReturnNextDividend) {
		this.percentReturnNextDividend = percentReturnNextDividend;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

}
