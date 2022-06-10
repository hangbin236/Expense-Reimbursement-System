package service;

import java.util.List;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import model.ReimbursementPojo;

public class ReimbursementServiceImpl implements ReimbursementService{
	
	ReimbursementDao reimbursementDao;
	
	

	public ReimbursementServiceImpl() {
		reimbursementDao = new ReimbursementDaoImpl();
	}

	@Override
	public List<ReimbursementPojo> getAllRequests(String status) {
		return reimbursementDao.getAllRequests(status);
	}

	@Override
	public List<ReimbursementPojo> getEmployeeRequests(int emp_id) {
		return reimbursementDao.getEmployeeRequests(emp_id);
	}

	@Override
	public ReimbursementPojo updateRequest(int rb_id, String newStatus) {
		return reimbursementDao.updateRequest(rb_id, newStatus);
	}

	@Override
	public boolean submitRequest(int emp_id, double amount) {
		return reimbursementDao.submitRequest(emp_id, amount);
	}

	@Override
	public List<ReimbursementPojo> viewMyRequests(int emp_id, String status) {
		return reimbursementDao.viewMyRequests(emp_id, status);
	}

}
