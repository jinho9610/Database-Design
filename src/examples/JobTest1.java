package examples;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.JobDAO;
import examples.dto.Job;

/**
 * Servlet implementation class JobTest1
 */
@WebServlet("/JobTest1")
public class JobTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobTest1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DAO add doGet() 호출!");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		JobDAO dao = new JobDAO();
		
		Job job = null;
		job=dao.getJob(1);
		if(job==null) out.println("<h1> My Job is null</h1>");
		else out.println("<h1> My Job " + job.getJobId() + "번 : " + job.getDescription() + "!!!</h1>");
		
		Job job1 = new Job(9, "temp");
		
		int addCount = dao.addJob(job1);
		out.println("<h1> insert : " + addCount + " row(s) </h1>");
		
		int delCount = dao.deleteJob(1);
		out.println("<h1> delete : " + delCount + " row(s) </h1>");
		
		Job job2=new Job(9, "updated");
		int updateCount = dao.updateJob(job2);
		out.println("<h1> update : " + updateCount + " row(s) </h1>");
		
		List<Job> jobList = dao.getJobs();
		
		for(Job job3 : jobList) {
			out.println("<h1> My Job " + job3.getJobId()+"번 : " + job3.getDescription() + "!!!</h1>");
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
