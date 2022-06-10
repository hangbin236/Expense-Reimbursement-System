package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ReimbursementPojo;

public class ReimbursementDaoImpl implements ReimbursementDao {

	@Override
	public List<ReimbursementPojo> getAllRequests(String status) {
		try {
			List<ReimbursementPojo> reimbursements = new ArrayList<ReimbursementPojo>();
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM reimbursement_details WHERE rb_status = '" + status + "';";
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				reimbursements.add(new ReimbursementPojo(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getDouble(3), resultSet.getTimestamp(4), resultSet.getInt(5)));
			}

			return reimbursements;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReimbursementPojo> getEmployeeRequests(int emp_id) {
		try {
			List<ReimbursementPojo> reimbursements = new ArrayList<ReimbursementPojo>();
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM reimbursement_details WHERE emp_id = " + emp_id + ";";
			ResultSet resultSet = stmt.executeQuery(query);
			
			while(resultSet.next()) {
				reimbursements.add(new ReimbursementPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
						resultSet.getTimestamp(4), resultSet.getInt(5)));
			}
			
			return reimbursements;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ReimbursementPojo updateRequest(int rb_id, String newStatus) {
		Statement stmt;
		Statement stmt2;
		Statement stmt3;
		ReimbursementPojo rbUpdatePojo = null;
		
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM reimbursement_details WHERE rb_id=" + rb_id +";";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				stmt2 = conn.createStatement();
				String query2 = "UPDATE reimbursement_details SET rb_status='" + newStatus + "' WHERE rb_id=" + rb_id +";";
				int rowsAffected = stmt2.executeUpdate(query2);
				
				if(rowsAffected == 1) {
					stmt3 = conn.createStatement();
					String query3 = "SELECT * FROM reimbursement_details WHERE rb_id=" + rb_id +";";
					ResultSet rs2 = stmt3.executeQuery(query3);
					
					if(rs2.next()) {
						rbUpdatePojo = new ReimbursementPojo();
					}
					
				}

			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rbUpdatePojo;
	}

	@Override
	public boolean submitRequest(int emp_id, double amount) {
		try {
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO reimbursement_details(rb_status, rb_amount, rb_timestamp, emp_id) "
					+ "VALUES ('pending', " + amount + ", current_timestamp, " + emp_id + ");";
			return stmt.executeUpdate(query) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ReimbursementPojo> viewMyRequests(int emp_id, String status) {
		try {
			List<ReimbursementPojo> requests = new ArrayList<ReimbursementPojo>();
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM reimbursement_details WHERE emp_id = " + emp_id + " AND rb_status = '" + status
					+ "';";
			ResultSet resultSet = stmt.executeQuery(query);
			while(resultSet.next()) {
				requests.add(new ReimbursementPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
						resultSet.getTimestamp(4), resultSet.getInt(5)));
			}
			return requests;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
