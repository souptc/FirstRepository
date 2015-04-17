package Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJSonParser {
	private String urlPattern = "http://q.stock.sohu.com/hisHq?code=%s&start=%s&end=%s";
	
	public List<StockHistory> getStockHistory(String code, String start, String end) throws JSONException, IOException
	{
		String address = String.format(urlPattern, code, start, end);
		URL url = new URL(address);
		// read from the URL
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	 
	    // build a JSON object
	    JSONObject obj = new JSONArray(str).getJSONObject(0);
	    if (obj.getInt("status") != 0)
	        return null;
	    List<StockHistory> lvResult = new ArrayList<StockHistory>();
	    JSONArray infos = obj.getJSONArray("hq");
	    for (int i = 0; i < infos.length(); i++)
	    {
	    	JSONArray info = infos.getJSONArray(i);
	    	StockHistory stock = new StockHistory();
	    	stock.setDate(info.getString(0));
	    	stock.setOpen_price(info.getString(1));
	    	stock.setClose_price(info.getString(2));
	    	stock.setDiff_price(info.getString(3));
	    	stock.setDiff_ratio(info.getString(4));
	    	stock.setHigh_price(info.getString(5));
	    	stock.setLow_price(info.getString(6));
	    	stock.setTran_volume(info.getString(7));
	    	stock.setTran_money(info.getString(8));
	    	stock.setExchage_ratio(info.getString(9));
	    	lvResult.add(stock);
	    }
	    return lvResult;
	}
}
