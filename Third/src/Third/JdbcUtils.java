package Third;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JdbcUtils {
	
	private static DataSource ds = null;

	static {
		try {
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			if(in == null)
				System.out.println("in is null");
			else 
				System.out.println("in is not null");
			
			Properties prop = new Properties();
			prop.load(in);
			System.out.println(prop);
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void release (Connection conn, Statement stmt, ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs  = null;
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if(conn != null) {
			try {

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	
	

}
