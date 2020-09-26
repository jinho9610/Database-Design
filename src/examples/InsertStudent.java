package examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

/**
 * Servlet implementation class InsertStudent
 */
@WebServlet("/InsertStudent")
public class InsertStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStudent() {
        super();
        System.out.println("InsertStudent 생성!!");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("InsertStudent_doGet() 호출!");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int StudentNumber = Integer.parseInt(request.getParameter("studentNumber"));
		String Name = request.getParameter("name");
		String Major = request.getParameter("major");
		int Grade = Integer.parseInt(request.getParameter("grade"));
		String SecondMajor = request.getParameter("secondMajor");
		String AdmissionDate = request.getParameter("admissionDate");
		String Email = request.getParameter("email");
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mysql://localhost/db_design";
			
			conn = DriverManager.getConnection(url, "12161719", "p12161719");
			System.out.println("연결 성공!!~");
			
			// 3. Query 수행을 위한 Statement 객체 생성
			String sql = "INSERT INTO STUDENT(StudentNumber, Name, Major, Grade, SecondMajor, AdmissionDate, Email) VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL 쿼리 작성
			pstmt.setInt(1, StudentNumber);
			pstmt.setString(2, Name);
			pstmt.setString(3, Major);
			pstmt.setInt(4, Grade);
			pstmt.setString(5, SecondMajor);
			pstmt.setString(6, AdmissionDate);
			pstmt.setString(7, Email);
			
			int count = pstmt.executeUpdate();
			System.out.println("데");
			// 5. 쿼리 수행
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("드라이버 로딩 실패");
		}
		catch(SQLException e)
		{
			System.out.println("에러 " + e);
		}
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
