package stockDB.parser;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import stockDB.Exception.StockNotExistException;
import stockDB.Model.StockTransactionInfo;

public class StockHistoryTranParser {
	private String urlPattern = "http://q.stock.sohu.com/hisHq?code=cn_%s&start=%s&end=%s";
	private String dateFormat = "yyyy-MM-dd";
	
	public List<StockTransactionInfo> getStockHistory(String code, String start, String end) throws JSONException, IOException, ParseException, StockNotExistException
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
	    {
	    	String msg = obj.getString("msg");
	    	if (msg != null && msg.contains("non-existent"))
	    		throw new StockNotExistException();
	        return null;
	    }
	    List<StockTransactionInfo> lvResult = new ArrayList<StockTransactionInfo>();
	    JSONArray infos = obj.getJSONArray("hq");
	    for (int i = 0; i < infos.length(); i++)
	    {
	    	JSONArray info = infos.getJSONArray(i);
	    	StockTransactionInfo stock = new StockTransactionInfo();
	    	String date = info.getString(0);
	    	SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	    	
	    	stock.setDate(format.parse(date));
	    	stock.setOpen_price(Float.parseFloat(info.getString(1)));
	    	stock.setClose_price(Float.parseFloat(info.getString(2)));
	    	stock.setDiff_price(Float.parseFloat(info.getString(3)));
	    	stock.setDiff_ratio(info.getString(4));
	    	stock.setHigh_price(Float.parseFloat(info.getString(5)));
	    	stock.setLow_price(Float.parseFloat(info.getString(6)));
	    	stock.setTran_volume(Float.parseFloat(info.getString(7)));
	    	stock.setTran_money(Float.parseFloat(info.getString(8)));
	    	stock.setExchage_ratio(info.getString(9));
	    	lvResult.add(stock);
	    }
	    return lvResult;
	}
}
