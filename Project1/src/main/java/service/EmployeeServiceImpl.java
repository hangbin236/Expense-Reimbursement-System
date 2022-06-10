package service;

import dao.EmployeeDao;
import model.EmployeePojo;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao;

	@Override
	public EmployeePojo validateLogin(String email, String password) {
		return employeeDao.validateLogin(email, password);
	}

	@Override
	public EmployeePojo logout(int emp_id) {
		return employeeDao.logout(emp_id);
	}

	@Override
	public EmployeePojo getEmployee(int emp_id) {
		return employeeDao.getEmployee(emp_id);
	}

	@Override
	public boolean updateEmployee(int emp_id, String changeColumn, String newInfo) {
		return employeeDao.updateEmployee(emp_id, changeColumn, newInfo);
	}

}
