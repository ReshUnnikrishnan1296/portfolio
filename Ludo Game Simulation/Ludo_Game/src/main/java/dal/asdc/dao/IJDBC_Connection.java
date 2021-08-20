package dal.asdc.dao;

import java.sql.Connection;
import java.util.Map;

/**
 * @author Reshma Unnikrishnan**/

public interface IJDBC_Connection {
	
	public Map<String,String> read_db_property_file();
	public Connection createDBConnection();
}
