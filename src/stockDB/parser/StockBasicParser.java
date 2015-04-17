package stockDB.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import stockDB.Model.*;

public class StockBasicParser {
	private String urlPattern;
	private String namePattern = "h1 > center > span";
	private String tablePattern = "div#list3 > table > tbody > tr > td";
	
	private int retryCount = 3;
	
	public void setUrlPattern(String urlPattern)
	{
		this.urlPattern = urlPattern;
	}
	
	public StockBasic getStockInfo(String stockId) throws InterruptedException 
	{
		int i = 3;
		while (i <= retryCount)
		{
			try
			{
				StockBasic lStock = getStockInfoWithoutRetry(stockId);
				if (lStock != null)
					return lStock;
				else
				{
					System.out.println("Stock: "+ stockId + " not found!");
					Thread.sleep(150);
					return null;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			Thread.sleep(500);
			i++;
		}
		return null;
	}
	
	public StockBasic getStockInfoWithoutRetry(String stockId) throws IOException
	{
		if (urlPattern == null || urlPattern == "")
			return null;
		String url = urlPattern + stockId + ".shtml";
			Document doc = Jsoup.connect(url).get();
			StockBasic lBasic = new StockBasic();
			lBasic.setStock_id(stockId);
			//name part
			Element ele = doc.select(namePattern).first();
			if (ele == null)
				return null;
			lBasic.setStock_name(ele.text());
			//detail
			Elements eles = doc.select(tablePattern);
			if (eles.size() == 0)
				return null;
			int n = eles.size();
			int i = 0;
			while (i < n)
			{
				Element e = eles.get(i);
				String lText = e.text();
				if (e.text().equals("所属行业") && i < n - 1)
				{
					lBasic.setBusiness(eles.get(++i).text());
				}
				else if (e.text().equals("所属地区") && i < n - 1)
				{
					lBasic.setLocation(eles.get(++i).text());
				}
				i++;
			}
			return lBasic;
	}
}
