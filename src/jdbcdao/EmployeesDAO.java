package jdbcdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class EmployeesDAO {
	
	public void insert(Employees emp) {
		Helper helper = new Helper();
		Connection conn = helper.getConn();
		String sql = "insert into employees(lastname,firstname,birthdate) values(?,?,?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, emp.getLastname());
			stmt.setString(2, emp.getFirstname());
			stmt.setDate(3, emp.getBirthdate());
			stmt.execute();
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			helper.closeConn(conn);
		}
	}
	
	public void update(Employees emp) {
		String lastname = emp.getLastname() ;
		String firstname = emp.getFirstname();
		Date birthDate = emp.getBirthdate();
		Helper helper = new Helper();
		Connection conn = helper.getConn();
		
		if(lastname == "") {
			String sqllastname = "select lastname from employees where employeeid = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sqllastname);
				stmt.setString(1, emp.getEmployeeid());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					lastname = rs.getString("lastname");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//helper.closeConn(conn);
			}
		}
		
		if(firstname == "") {
			String sqllastname = "select firstname from employees where employeeid = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sqllastname);
				stmt.setString(1, emp.getEmployeeid());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					firstname = rs.getString("firstname");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//helper.closeConn(conn);
			}
		}
		
		if(birthDate == null) {
			String sqllastname = "select birthDate from employees where employeeid = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sqllastname);
				stmt.setString(1, emp.getEmployeeid());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					birthDate = rs.getDate("birthDate");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//helper.closeConn(conn);
			}
		}
		
		
		
		String sql = "update employees set lastname = ? , firstname = ? , birthDate = ?"
				+ "where employeeid = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, lastname);
			stmt.setString(2, firstname);
			stmt.setDate(3, birthDate);
			stmt.setString(4, emp.getEmployeeid());
			stmt.execute();
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			helper.closeConn(conn);
		}
	}
	
	public void remove(Employees emp) {
		Helper helper = new Helper();
		Connection conn = helper.getConn();
		String sql = "delete from employees where lastname = ? and firstname = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, emp.getLastname());
			stmt.setString(2, emp.getFirstname());
			stmt.execute();
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			helper.closeConn(conn);
		}
	}
	
	public List<String[]> selectAll(){
		List<String[]> ret = new ArrayList<String[]>();
		Helper helper = new Helper();
		Connection conn = helper.getConn();
		String sql = "select employeeid,lastname,firstname,birthdate from employees";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String[] emp = new String[4];
				emp[0] = rs.getString("employeeid");
				emp[1] = rs.getString("lastname");
				emp[2] = rs.getString("firstname");
				
				Date date = rs.getDate("birthdate");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String datestr = df.format(date);
				emp[3] = datestr;
				
				ret.add(emp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			helper.closeConn(conn);
		}
		return ret;
	}

}
