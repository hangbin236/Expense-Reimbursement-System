package presentation;

import io.javalin.Javalin;
import model.EmployeePojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ReimbursementService;
import service.ReimbursementServiceImpl;

public class ExpenseReimbursement {

	public static void main(String[] args) {
		
		EmployeeService emplyeeService = new EmployeeServiceImpl();
		ReimbursementService reimburse = new ReimbursementServiceImpl();
		
		Javalin server = Javalin.create(); 
		server.start(7474);
		
		server.get("/login", (ctx)->{
			
			String email = ctx.formParam("email");
			String password = ctx.formParam("password");
			EmployeePojo user = emplyeeService.validateLogin(email, password);
			if (user == null) {
				ctx.status(400);
			} else {
				ctx.sessionAttribute("emp_id", user.getEmp_id());
			}
			ctx.json(user);
		});
				
	}

}
