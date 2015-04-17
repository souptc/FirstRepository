package stockDB.DBConnector;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import stockDB.Model.*;

public class StockBasicDAO {
	private DataSource dataSource;
	
	private String _queryString = "Select stock_id, stock_name, location, business from stock_basic_info where stock_id=?";
	
	private String _insertString = "INSERT INTO `stock_basic_info` (`stock_id`, `stock_name`, `business`, `location`) VALUES (?, ?, ?, ?)";
	
	private String _createTranString = "CREATE TABLE `stock`.`?` ("
			+ "  `date` DATE NOT NULL,  `open_price` float NULL,"
			+ " `close_price` float NULL,"
			+ " `diff_price` float NULL,"
			+ " `diff_ratio` VARCHAR(45) NULL,"
			+ " `high_price` float NULL,"
			+ " `low_price` float NULL,"
			+ " `tran_volume` float NULL,"
			+ " `tran_money` float NULL,"
			+ " `exchage_ratio` VARCHAR(45) NULL,"
			+ " PRIMARY KEY (`date`),"
			+ " UNIQUE INDEX `date_UNIQUE` (`date` ASC))";
	
	private String _queryTransactionString = "select date, open_price, close_price, diff_price, diff_ratio, high_price,"
			+ " low_price, tran_volume, tran_money, exchage_ratio from * "
			+ " where date >= ? and date <= ?";
	
	private String _idListString = "select stock_id from stock_basic_info";
	
	private String _maxDateString = "select max(date) as m_date from *";
	
	private String _insertTranString = "INSERT INTO `stock_transaction_*` (`date`, `open_price`, `close_price`,"
			+" `diff_price`, `diff_ratio`, `high_price`, `low_price`,"
			+" `tran_volume`, `tran_money`, `exchage_ratio`) VALUES (?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
	
	public void insertRecord(String id, StockTransactionInfo data)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = _insertTranString.replace("*", id);
		template.update(sql, data.getDate(), data.getOpen_price(), data.getClose_price(),
				data.getDiff_price(), data.getDiff_ratio(),data.getHigh_price(),
				data.getLow_price(),data.getTran_volume(), data.getTran_money(),
				data.getExchage_ratio());
	}
	
	public Date getMaxDate(String id)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = _maxDateString.replace("*", "stock.stock_transaction_" + id);
		List<Date> result = template.query(sql, new RowMapper<Date>() 
				{
					public Date mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						return rs.getDate("m_date");
					}
				});
		return result.isEmpty() ? null : result.get(0);
	}
	
	public void createTranTable(String id)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = _createTranString;
		sql = sql.replace("?", "stock_transaction_"+id);
		template.execute(sql);
	}
	
	@SuppressWarnings("unchecked")
	public StockBasic getStock(String id)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<StockBasic> list = template.query(_queryString, new Object[] {id}, new RowMapper<StockBasic>()
				{
					public StockBasic mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						StockBasic model = new StockBasic();
						model.setStock_id(rs.getString("stock_id"));
						model.setStock_name(rs.getString("stock_name"));
						model.setLocation(rs.getString("location"));
						model.setBusiness(rs.getString("business"));
						return model;
					}
				});
		return list == null || list.size() == 0 ? null : list.get(0);
	}
	
	public List<StockTransactionInfo> getStockTransactionList(String id, String start, String end)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = _queryTransactionString.replace("*", "stock_transaction_"+id);
		return template.query(sql, new Object[] {start, end}, new RowMapper<StockTransactionInfo>()
				{
					public StockTransactionInfo mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						StockTransactionInfo model = new StockTransactionInfo();
						model.setDate(rs.getDate("date"));
						model.setOpen_price(rs.getFloat("open_price"));
						model.setClose_price(rs.getFloat("close_price"));
						model.setHigh_price(rs.getFloat("high_price"));
						model.setLow_price(rs.getFloat("low_price"));
						model.setDiff_price(rs.getFloat("diff_price"));
						model.setDiff_ratio(rs.getString("diff_ratio"));
						model.setTran_volume(rs.getFloat("tran_volume"));
						model.setTran_money(rs.getFloat("tran_money"));
						model.setExchage_ratio(rs.getString("exchage_ratio"));
						return model;
					}
				});
	}
	
	public List<String> getStockIDList()
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return template.query(_idListString, new RowMapper<String>()
				{
					public String mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						return rs.getString("stock_id");
					}
				});
	}
	
	public void setDataSource(DataSource iDataSource)
	{
		dataSource = iDataSource;
	}
	
	public void insertStorck(StockBasic stock)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update(_insertString, stock.getStock_id(), stock.getStock_name(), stock.getBusiness(), stock.getLocation());
	}
}
