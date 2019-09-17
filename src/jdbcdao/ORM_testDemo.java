package jdbcdao;

import java.util.List;

public class ORM_testDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employees emp = new Employees();
		emp.setLastname("FoFo");
		emp.setFirstname("Lung");
		emp.setBirthdate("1990-07-11");
		
		EmployeesDAO dao = new EmployeesDAO();
		dao.insert(emp);
		
		
		List<String[]> tests = dao.selectAll();
		for(String[] test : tests) {
			for(String str : test) {
				System.out.print(str+" ");	
			}
			System.out.println();
		}
	}

}
