package Test;

public class StockBasic {
	private String stock_id;
	private String stock_name;
	private String business;
	
	public StockBasic()
	{
	}
	
	public String getStock_id()
	{
		return stock_id;
	}
	
	public String getStock_name()
	{
		return stock_name;
	}
	
	public String getBusiness()
	{
		return business;
	}
	
	public void setStock_id(String stock_id)
	{
		this.stock_id = stock_id;
	}
	
	public void setStock_name(String stock_name)
	{
		this.stock_name = stock_name;
	}
	
	public void setBusiness(String business)
	{
		this.business = business;
	}
	
	public String getMessage()
	{
		return "StockID: " +stock_id + ", Name: " + stock_name + ", Business: " + business;  
	}
}
