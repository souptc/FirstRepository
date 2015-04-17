package Test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJSoup {
	private String urlPattern;
	private String namePattern = "h1 > center > span";
	private String tablePattern = "div#list3 > table > tbody > tr > td";
	
	public void setUrlPattern(String urlPattern)
	{
		this.urlPattern = urlPattern;
	}
	
	public StockBasic getStockInfo(String stockId)
	{
		if (urlPattern == null || urlPattern == "")
			return null;
		String url = urlPattern + stockId + ".shtml";
		try
		{
			Document doc = Jsoup.connect(url).get();
			StockBasic lBasic = new StockBasic();
			lBasic.setStock_id(stockId);
			//name part
			Element ele = doc.select(namePattern).first();
			lBasic.setStock_name(ele.text());
			//detail
			Elements eles = doc.select(tablePattern);
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
				i++;
			}
			return lBasic;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
