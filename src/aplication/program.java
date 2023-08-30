package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class program {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DB.getConnection();
			statement = connection.prepareStatement(
					"DELETE FROM department "
					+"WHERE "
					+"Id = ?");
			
			statement.setInt(1, 6);
			
			int rowsAffected = statement.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected );
			
		} catch ( SQLException e) {
			throw new  DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}

}
