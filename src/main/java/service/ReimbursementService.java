package service;

import java.util.List;

import model.ReimbursementPojo;

public interface ReimbursementService {
	List<ReimbursementPojo> getAllRequests(String status);

	List<ReimbursementPojo> getEmployeeRequests(int emp_id);

	ReimbursementPojo updateRequest(int rb_id, String newStatus);

	boolean submitRequest(int emp_id, double amount);

	List<ReimbursementPojo> viewMyRequests(int emp_id, String status);
}
