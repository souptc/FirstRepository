package stockDB;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import stockDB.DBConnector.StockBasicDAO;
import stockDB.Exception.StockNotExistException;
import stockDB.Model.StockTransactionInfo;
import stockDB.parser.StockHistoryTranParser;

public class StockHistoryCollector {
	private StockBasicDAO dao;
	private String dbDateFormat = "yyyy-MM-dd";
	private String defaultDate = "2009-01-07";
	private String siteDateFormat = "yyyyMMdd";
	private int retryCount = 3;

	public StockBasicDAO getDao() {
		return dao;
	}

	public void setDao(StockBasicDAO dao) {
		this.dao = dao;
	}

	public Boolean collect(String id) throws ParseException {
		Date today = Calendar.getInstance().getTime();
		Date last = dao.getMaxDate(id);

		SimpleDateFormat format = new SimpleDateFormat(dbDateFormat);
		SimpleDateFormat format2 = new SimpleDateFormat(siteDateFormat);
		Calendar calendar = Calendar.getInstance();
		if (last == null) {
			last = format.parse(defaultDate);
		}
		calendar.setTime(last);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date cur = calendar.getTime();
		

		while (cur.compareTo(today) <= 0) {
			calendar.setTime(cur);
			calendar.add(Calendar.MONTH, 3);
			Date end = calendar.getTime();
			if (end.compareTo(today) > 0)
				end = today;

			String lStart = format2.format(cur);
			String lEnd = format2.format(end);
			System.out.println("start get data for id: " + id + " from: "
					+ lStart + " to: " + lEnd);
			List<StockTransactionInfo> list = null;
			try {
				list = getInfoWithRetry(id, lStart, lEnd);
			} catch (StockNotExistException e) {
				// TODO Auto-generated catch block
				return false;
			}
			System.out.println("success get data from site for id: " + id
					+ " from: " + lStart + " to: " + lEnd);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					dao.insertRecord(id, list.get(i));
				}
			} else {
				System.out.println("Exception happened at stock: " + id
						+ " during: from: " + lStart + " to:" + lEnd);
			}
			System.out.println("End get data for id: " + id + " from: "
					+ lStart + " to: " + lEnd);
			calendar.setTime(end);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			cur = calendar.getTime();
		}
		return true;
	}

	private List<StockTransactionInfo> getInfoWithRetry(String id,
			String start, String end) throws StockNotExistException{
		StockHistoryTranParser lParser = new StockHistoryTranParser();
		int i = 0;
		while (i < retryCount) {
			try {
				List<StockTransactionInfo> lvResult = lParser.getStockHistory(
						id, start, end);
				if (lvResult != null)
					return lvResult;
				i++;
			}
			catch(StockNotExistException e)
			{
				throw e;
			}
			catch(JSONException e)
			{
				return null;
			}
			catch(ParseException e)
			{
				return null;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				i++;
			}
		}
		return null;
	}
}
