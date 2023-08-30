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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DB.getConnection();
			statement = connection.prepareStatement(
					 "INSERT INTO seller " 
			        + "(Name, Email, BirthDate, BaseSalary, DepartmentId) " 
					+ "VALUES " 
					+ "( ?, ?, ?, ?, ?)");
			statement.setString(1, "Carl Perple");
			statement.setString(2, "carl@gmail.com");
			statement.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			statement.setDouble(4, 3000.00);
			statement.setInt(5, 4);
			
			int rowsAffected = statement.executeUpdate();
			System.out.println("Done!  Rows affected: " + rowsAffected );
		}catch (SQLException | ParseException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}

}
