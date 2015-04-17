package stockDB.Model;

import java.util.Date;

public class StockTransactionInfo {
	private Date date;
	private float open_price;
	private float close_price;
	private float diff_price;
	private String diff_ratio;
	private float high_price;
	private float low_price;
	private float tran_volume;
	private float tran_money;
	private String exchage_ratio;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getOpen_price() {
		return open_price;
	}
	public void setOpen_price(float open_price) {
		this.open_price = open_price;
	}
	public float getClose_price() {
		return close_price;
	}
	public void setClose_price(float close_price) {
		this.close_price = close_price;
	}
	public float getDiff_price() {
		return diff_price;
	}
	public void setDiff_price(float diff_price) {
		this.diff_price = diff_price;
	}
	public String getDiff_ratio() {
		return diff_ratio;
	}
	public void setDiff_ratio(String diff_ratio) {
		this.diff_ratio = diff_ratio;
	}
	public float getHigh_price() {
		return high_price;
	}
	public void setHigh_price(float high_price) {
		this.high_price = high_price;
	}
	public float getLow_price() {
		return low_price;
	}
	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}
	public float getTran_volume() {
		return tran_volume;
	}
	public void setTran_volume(float tran_volume) {
		this.tran_volume = tran_volume;
	}
	public float getTran_money() {
		return tran_money;
	}
	public void setTran_money(float tran_money) {
		this.tran_money = tran_money;
	}
	public String getExchage_ratio() {
		return exchage_ratio;
	}
	public void setExchage_ratio(String exchage_ratio) {
		this.exchage_ratio = exchage_ratio;
	}
	
}
