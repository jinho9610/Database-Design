package examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

/**
 * Servlet implementation class JDBCservlet
 */
@WebServlet("/JDBCservlet")
public class JDBCservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCservlet() {
        super();
        System.out.println("JDBCservlet 생성!!");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출!");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1> Hello World!!!</h1>");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
			
		try
		{
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mysql://localhost/db_design";
			
			conn = DriverManager.getConnection(url, "12161719", "p12161719");
			System.out.println("연결 성공!!~");
			
			// 3. Query 수행을 위한 Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL 쿼리 작성
			String sql = "SELECT StudentNumber, Name, Major, Grade, SecondMajor, AdmissionDate, Email FROM student";
			
			// 5. 쿼리 수행
			rs = stmt.executeQuery(sql);
			
			// 6. 실행 결과 출력하기
			while(rs.next()) 
			{
				int StudentNumber =rs.getInt(1);
				String Name = rs.getString(2);
				String Major = rs.getString(3);
				int Grade = rs.getInt(4);
				String SecondMajor = rs.getString(5);
				if(SecondMajor == null) SecondMajor = "";
				String AdmissionDate = rs.getString(6);
				if(AdmissionDate == null) AdmissionDate = "";
				String Email = rs.getString(7);
				if(Email == null) Email = "";
			
				out.println("<h1>" + StudentNumber + ", " + Name + ", " + Major + ", " + Grade
						+ ", " + SecondMajor + ", " + AdmissionDate + ", " + Email + "</h1>");
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("드라이버 로딩 실패");
		}
		catch(SQLException e)
		{
			System.out.println("에러 " + e);
		}
		finally
		{
			try
			{
				if( conn != null && !conn.isClosed()) conn.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(rs != null && !rs.isClosed()) rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
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
