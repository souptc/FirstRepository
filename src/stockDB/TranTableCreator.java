package stockDB;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stockDB.DBConnector.StockBasicDAO;

public class TranTableCreator {
	
	public void Test()
	{
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext("Beans.xml");
		
		StockBasicDAO stockDAO = (StockBasicDAO) context.getBean("stockBasicDAO");
		
		List<String> stockIDList = stockDAO.getStockIDList();
		
		for(int i = 0; i < 1; i++)
		{
			stockDAO.createTranTable(stockIDList.get(i));
			System.out.println("Create table for ID: " + stockIDList.get(i));
		}
		
	}
	public void CreateTable()
	{
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext("Beans.xml");
		
		StockBasicDAO stockDAO = (StockBasicDAO) context.getBean("stockBasicDAO");
		
		List<String> stockIDList = stockDAO.getStockIDList();
		
		for(int i = 0; i < stockIDList.size(); i++)
		{
			stockDAO.createTranTable(stockIDList.get(i));
			System.out.println("Create table for ID: " + stockIDList.get(i));
		}
	}
}
