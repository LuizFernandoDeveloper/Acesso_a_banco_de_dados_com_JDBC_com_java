package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    private static Connection  connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try{

                Properties properties =  loaProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);

            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }

        }
        return connection;
    }

    private static Properties loaProperties(){
        try (FileInputStream fs = new FileInputStream("db.properties")) {

            Properties props = new Properties();
            props.load(fs);
            return props;

        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeConnection(){
        if(connection != null){
            try{

                connection.close();

            }catch(SQLException e){

                throw new  DbException(e.getMessage());
            }
        }
    }
    
    public static void  closeStatement(Statement st) {
    	if(st != null) {
    		try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
    	}
    }
    
    public static void  closeResultSet(ResultSet rs) {
    	if(rs != null) {
    		try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
    	}
    }
}
