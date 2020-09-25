package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Job;

public class JobDAO {
	private static String dburl = "jdbc:mysql://localhost/lect4?serverTimezone=Asia/Seoul";
	private static String dbUser = "12161719";
	private static String dbpasswd = "p12161719";

	public Job getJob(Integer jobId) {
		Job job = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT job_id, description FROM job WHERE job+id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, jobId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt(1);
					String description = rs.getString(2);
					job = new Job(id, description);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return job;
	}

	public int addJob(Job job) {
		int insertCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO job (job_id, description) VALUES (?, ?)";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, job.getJobId());
			ps.setString(2, job.getDescription());

			insertCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}

	public int deleteJob(Integer jobId) {
		int deleteCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM job WHERE job_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, jobId);
			deleteCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}

	public int updateJob(Job job) {
		int updateCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "update job set description = ? where job_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1,  job.getDescription());
			ps.setInt(2,  job.getJobId());
			
			updateCount = ps.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}
	
	public List<Job> getJobs() {
		List<Job> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT description, job_id FROM job order by job_id desc";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String description = rs.getString(1);
					int id=rs.getInt("job_id");
					Job job=new Job(id, description);
					list.add(job);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
