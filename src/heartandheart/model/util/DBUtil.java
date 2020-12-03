package heartandheart.model.util;

import java.io.FileInputStream;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static Properties p = new Properties();
	private static Properties sqlAll = new Properties();
	
	static {
		try {
			p.load( new FileInputStream("db.properties"));
			Class.forName("jdbc.driver");
			sqlAll.load(new FileInputStream("allsql.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(p.getProperty("jdbc.url")
				,p.getProperty("jdbc.id")
				,p.getProperty("jdbc.pw"));
	}
		
	public static Properties getSqlAll() {
		return sqlAll;
	}

	public static void close(Connection con, Statement stmt) {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}
