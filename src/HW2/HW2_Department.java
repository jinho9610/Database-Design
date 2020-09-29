package HW2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.DepartmentDAO;
import examples.dto.Department;

/**
 * Servlet implementation class HW2_Department
 */
@WebServlet("/HW2_Department")
public class HW2_Department extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HW2_Department() {
		super();
		System.out.println("HW2_Department start point");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("HW2_Department.java doGet start point");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 출력용 객체 생성
		DepartmentDAO dao = new DepartmentDAO(); // DAO 객체 생성

		Enumeration<String> e = request.getParameterNames();
		ArrayList<String> params = new ArrayList<String>();

		while (e.hasMoreElements()) {
			params.add(e.nextElement());
		} // request의 모든 parameter 개수 구하기
		int param_count = params.size() - 1; // submit 버튼을 제외한 실제 parameter 수
		System.out.println(params.get(0));

		if (param_count == 1) {
			int dnumber = Integer.parseInt(request.getParameter(params.get(0)));

			if (params.get(param_count).equals("select")) {
				Department department = dao.getDepartment(dnumber);

				if (department == null) // 검색 결과가 없으면
					out.println("<h1>그런 번호의 부서는 존재하지 않습니다.</h1>");
				else {
					out.println("<h1>해당 번호의 부서는 다음과 같습니다.<br>");
					out.println("DName: " + department.getDname() + "<br>");
					out.println("Mgr_ssn: " + department.getMgr_ssn() + "<br>");
					out.println("Mgr_start_date: " + department.getMgr_start_date() + "</h1>");
				}
			} else { // submit 버튼을 제외한 parameter가 1개이지만 delete 버튼일 경우
				int delCount = dao.deleteDepartment(dnumber);
				out.println("<h1>" + delCount + " row(s) is(are) deleted</h1>");
			}
		}

		else if (param_count == 4) {
			String dname = request.getParameter(params.get(0));
			int dnumber = Integer.parseInt(request.getParameter(params.get(1)));
			String mgr_ssn = request.getParameter(params.get(2));
			String mgr_start_date = request.getParameter(params.get(3));

			if (params.get(param_count).equals("insert")) {
				Department department = new Department(dname, dnumber, mgr_ssn, mgr_start_date);
				int addCount = dao.addDepartment(department);
				out.println("<h1>" + addCount + " row(s) is(are) inserted</h1>");
			} else { // submit 버튼을 제외한 parameter가 4개이지만 update 버튼일 경우
				Department department = new Department(dname, dnumber, mgr_ssn, mgr_start_date);
				int updateCount = dao.updateDepartment(department);
				out.println("<h1>" + updateCount + " row(s) is(are) updated</h1>");
			}
		}

		else { // 전체 조회 즉, inquiry의 경우
			if (params.get(param_count).equals("inquiry")) {
				List<Department> departmentList = dao.getDepartments();
				for (Department department_tmp : departmentList) {
					out.println("<h1>My name is " + department_tmp.getDname() + "<br>");
					out.println("My number is " + department_tmp.getDnumber() + "<br>");
					out.println("My mgr_ssn is " + department_tmp.getMgr_ssn() + "<br>");
					out.println("My mgr_start_date is " + department_tmp.getMgr_start_date() + "</h1><br>");
				}
			}
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
