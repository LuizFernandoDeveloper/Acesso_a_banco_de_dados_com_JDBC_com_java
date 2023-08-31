package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.JoinRowSet;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class program {

	public static void main(String[] args)  {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DB.getConnection();
			connection.setAutoCommit(false);
			statement =  connection.createStatement();
			
			 int rows1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 2090 where DepartmentId = 1 ");
//			 int x = 1;
//			 if(x < 2) {
//				 throw new SQLException("Fake error");
//			 }
			 int rows2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3090 where DepartmentId = 2 ");
			 System.out.println("Rows1:  "+ rows1);
			 System.out.println("Rows2:  " + rows2);
			 
			 connection.commit();
			
		} catch (SQLException e) {
				try {
					connection.rollback();
					throw new  DbException("Transaction rolled back! caused by: " + e.getMessage());
				}catch (SQLException e1) {
					 throw new DbException("Erro trying  to rollback! Causede by: " + e1.getMessage());
				}
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}

}
