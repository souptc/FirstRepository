package Test;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestDB {
	private DataSource dataSource;
	
	private String _queryString = "Select stock_id, stock_name, business from stock_basic_info where stock_id=?";
	
	private String _insertString = "INSERT INTO `stock_basic_info` (`stock_id`, `stock_name`, `business`) VALUES (?, ?, ?)";
	
	public StockBasic getStock(String id)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return (StockBasic)template.queryForObject(_queryString, new Object[] {id}, new BeanPropertyRowMapper(StockBasic.class));
	}
	
	public void setDataSource(DataSource iDataSource)
	{
		dataSource = iDataSource;
	}
	
	public void insertStorck(StockBasic stock)
	{
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update(_insertString, stock.getStock_id(), stock.getStock_name(), stock.getBusiness());
	}
}
