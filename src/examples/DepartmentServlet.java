package examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.DepartmentDAO;
import examples.dto.Department;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartmentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DepartmentDAO add doGet() 호출!");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		DepartmentDAO dao = new DepartmentDAO();

		Department department = null;
		department = dao.getDepartment(1);
		if (department == null) {
			out.println("<h1> My name is null");
			out.println("<h1> My number is null");
			out.println("<h1> My mgr_ssn is null");
			out.println("<h1> My mgr_start_date is null</h1>");
			}
		else {
			out.println("<h1>My name is " + department.getDname() + "<br>");
			out.println("My number is " + department.getDnumber() + "<br>");
			out.println("My mgr_ssn is " + department.getMgr_ssn() + "<br>");
			out.println("My mgr_start_date is " + department.getMgr_start_date() + "</h1><br>");
		}

		int delCount = dao.deleteDepartment(1);
		out.println("<h1> delete : " + delCount + " row(s) </h1>");
		
		Department department_insert = new Department("PhdStudent", 1, "123456789", "2020-09-25");
		int addCount = dao.addDepartment(department_insert);
		out.println("<h1> insert : " + addCount + " row(s) </h1>");

		Department department_update = new Department("GraduateStudent", 26, "123456789", "2020-09-24");
		int updateCount = dao.updateDepartment(department_update);
		out.println("<h1> update : " + updateCount + " row(s) </h1>");

		List<Department> departmentList = dao.getDepartments();

		for (Department department_tmp : departmentList) {
			out.println("<h1>My name is " + department_tmp.getDname() + "<br>");
			out.println("My number is " + department_tmp.getDnumber() + "<br>");
			out.println("My mgr_ssn is " + department_tmp.getMgr_ssn() + "<br>");
			out.println("My mgr_start_date is " + department_tmp.getMgr_start_date() + "</h1><br>");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
