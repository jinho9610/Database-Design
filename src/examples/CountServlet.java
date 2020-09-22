package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountServlet
 */
@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int start = Integer.parseInt(request.getParameter("start_number"));
		int end = Integer.parseInt(request.getParameter("end_number"));
		out.println("<h1> Hello " + start + ", " + end + "</h1>");
		out.println("<br><h1>" + start + "부터" + end + "까지 출력합니다.</h1><br>");
		for(int i=start; i < end + 1; i++)
			out.print("<h1>" + i + "</h1>");
		out.close();
	}

}
