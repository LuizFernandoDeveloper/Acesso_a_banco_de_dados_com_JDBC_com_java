package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;

public class program {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DB.getConnection();
			statement = connection.prepareStatement(
					"UPDATE seller " 
					+"SET BaseSalary = BaseSalary + ? "
					+ "where "
					+"(DepartmentId = ?)");
			statement.setDouble(1, 200.0);
			statement.setInt(2, 2);
			
			int rowsAffected = statement.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected );
			
		} catch ( SQLException e) {
			throw new  DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}

}
