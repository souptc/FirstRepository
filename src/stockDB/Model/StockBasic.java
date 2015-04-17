package stockDB.Model;

public class StockBasic {
	private String stock_id;
	private String stock_name;
	private String location;
	private String business;
	private String value_total;
	private String value_market;
	
	public StockBasic()
	{
	}
	
	public String getStock_id()
	{
		return stock_id;
	}
	
	public String getLocation()
	{
		return location;
	}
		
	public String getStock_name()
	{
		return stock_name;
	}
	
	public String getBusiness()
	{
		return business;
	}
	
	public String getValue_total()
	{
		return value_total;
	}
	
	public String getValue_market()
	{
		return value_market;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public void setValue_total(String total)
	{
		this.value_total = total;
	}
	
	public void setValue_market(String market)
	{
		this.value_market = market;
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
