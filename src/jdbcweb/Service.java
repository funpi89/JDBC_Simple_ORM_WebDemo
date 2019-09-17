package jdbcweb;

import java.sql.Date;
import java.util.List;

import jdbcdao.Employees;
import jdbcdao.EmployeesDAO;

public class Service {
	
	public void addEmp(String lastname, String firstname, String birthdate) {
		birthdate = birthdate.replace("/", "-");
		Date date = Date.valueOf(birthdate);
		
		EmployeesDAO dao = new EmployeesDAO();
		Employees emp = new Employees();
		emp.setLastname(lastname);
		emp.setFirstname(firstname);
		emp.setBirthdate(birthdate);
		dao.insert(emp);
		
	}
	
	public void updateEmp(String employeeid, String lastname, String firstname, String birthdate) {
		Date date;
		if(birthdate == "") {
			date = null;
		}else {
			birthdate = birthdate.replace("/", "-");
			date = Date.valueOf(birthdate);
		}
		
		
		EmployeesDAO dao = new EmployeesDAO();
		Employees emp = new Employees();
		emp.setLastname(lastname);
		emp.setFirstname(firstname);
		emp.setBirthdate(birthdate);
		dao.update(emp);
		
	}
	
	public void removeEmp(String lastname, String firstname) {
		EmployeesDAO dao = new EmployeesDAO();
		Employees emp = new Employees();
		emp.setLastname(lastname);
		emp.setFirstname(firstname);
		dao.remove(emp);
		
	}
	
	public List<String[]> findAllEmp(){
		
		EmployeesDAO dao = new EmployeesDAO();
		List<String[]> ret = dao.selectAll();
		return ret;
	}

}
