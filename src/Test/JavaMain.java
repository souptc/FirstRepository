package Test;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Beans.xml");

		/*TestDB obj = (TestDB) context.getBean("stockBasicDAO");

	    StockBasic stock = obj.getStock("601398");
	    System.out.println(stock.getMessage());
	    
	    TestJSoup parser = (TestJSoup) context.getBean("stockInfoCrawler");
	    StockBasic lStock = parser.getStockInfo("002142");
	    System.out.println(lStock.getMessage());
	    
	    obj.insertStorck(lStock);
	    
	    stock = obj.getStock("002142");
	    System.out.println(stock.getMessage());*/
		TestJSonParser lParser = new TestJSonParser();
		try {
			List<StockHistory> lvList = lParser.getStockHistory("zs_002750", "20141201", "20141205");
			System.out.println("Hello");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
