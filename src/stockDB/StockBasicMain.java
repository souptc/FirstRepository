package stockDB;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stockDB.DBConnector.StockBasicDAO;
import stockDB.Model.StockBasic;
import stockDB.Model.StockTransactionInfo;
import stockDB.parser.StockBasicParser;

public class StockBasicMain {
	public static void main(String[] args)
	{
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext("Beans.xml");
				
		//TranTableCreator creator = new TranTableCreator();
		//creator.CreateTable();
		StockHistoryCollector collector = (StockHistoryCollector) context.getBean("stockInfoCollector");
		StockBasicDAO stockDAO = (StockBasicDAO) context.getBean("stockBasicDAO");
		
		List<String> stockIDList = stockDAO.getStockIDList();
		
		for(int i = 0; i < stockIDList.size(); i++)
		{
			System.out.println("Start collect for:"+stockIDList.get(i));
			try {
				collector.collect(stockIDList.get(i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("End collect for:" + stockIDList.get(i));
		}
		
		/*ApplicationContext context = 
	            new ClassPathXmlApplicationContext("Beans.xml");
		
		StockBasicDAO stockDAO = (StockBasicDAO) context.getBean("stockBasicDAO");
		StockBasicParser stockParser = (StockBasicParser) context.getBean("stockInfoParser");
		
		//60
		try
		{
		int lId = 603452;
		for (; lId < 610000; lId++)
		{
			StockBasic lStock = stockParser.getStockInfo("" +lId);
			if (lStock != null)
			{
				System.out.println(lStock.getMessage());
				stockDAO.insertStorck(lStock);
			}
		}
		//30
		lId = 300001;
		for (; lId < 310000; lId++)
		{
			StockBasic lStock = stockParser.getStockInfo("" +lId);
			if (lStock != null)
			{
				System.out.println(lStock.getMessage());
				stockDAO.insertStorck(lStock);
			}
		}
		//00
		NumberFormat nf = new DecimalFormat("000000");
		lId = 1;
		for (; lId < 10000; lId++)
		{
			StockBasic lStock = stockParser.getStockInfo("" +nf.format(lId));
			if (lStock != null)
			{
				System.out.println(lStock.getMessage());
				stockDAO.insertStorck(lStock);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}
}
