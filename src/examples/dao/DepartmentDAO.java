package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Department;

public class DepartmentDAO {
	private static String dburl = "jdbc:mysql://localhost/lect4?serverTimezone=Asia/Seoul";
	private static String dbUser = "12161719";
	private static String dbpasswd = "p12161719";

	public Department getDepartment(Integer dnumber) {
		Department department = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT dname, dnumber, mgr_ssn, mgr_start_date FROM department WHERE dnumber = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, dnumber); // sql의 ?를 dnumber로 채워넣음 // 즉 PK인 dnumber로 검색을 함

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String dname = rs.getString(1);
					int number = rs.getInt(2);
					String mgr_ssn = rs.getString(3);
					String mgr_start_date = rs.getString(4);
					
					department = new Department(dname, number, mgr_ssn, mgr_start_date);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return department;
	}

	public int addDepartment(Department department) {
		int insertCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO department (Dname, Dnumber, Mgr_ssn, Mgr_start_date) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1,  department.getDname());
			ps.setInt(2, department.getDnumber());
			ps.setString(3,  department.getMgr_ssn());
			ps.setString(4, department.getMgr_start_date());

			insertCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}

	public int deleteDepartment(Integer dnumber) {
		int deleteCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM department WHERE dnumber = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, dnumber);
			deleteCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}

	public int updateDepartment(Department department) {
		int updateCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "update department set dname = ?, mgr_ssn = ?, mgr_start_date = ? where dnumber = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, department.getDname());
			ps.setString(2, department.getMgr_ssn());
			ps.setString(3, department.getMgr_start_date());
			ps.setInt(4, department.getDnumber());

			updateCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}

	public List<Department> getDepartments() {
		List<Department> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT dname, mgr_ssn, mgr_start_date, dnumber FROM department order by dnumber desc";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String dname = rs.getString(1);
					String mgr_ssn = rs.getString(2);
					String mgr_start_date = rs.getString(3);
					int number = rs.getInt(4);
					
					Department department = new Department(dname, number, mgr_ssn, mgr_start_date);
					list.add(department);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
