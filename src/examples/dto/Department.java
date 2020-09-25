package examples.dto;

public class Department {
	private String dname;
	private Integer dnumber;
	private String mgr_ssn;
	private String mgr_start_date;
	
	public Department(String dname, Integer dnumber, String mgr_ssn, String mgr_start_date) {
		super();
		this.dname = dname;
		this.dnumber = dnumber;
		this.mgr_ssn = mgr_ssn;
		this.mgr_start_date = mgr_start_date;
	}
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Integer getDnumber() {
		return dnumber;
	}
	public void setDnumber(Integer dnumber) {
		this.dnumber = dnumber;
	}
	public String getMgr_ssn() {
		return mgr_ssn;
	}
	public void setMgr_ssn(String mgr_ssn) {
		this.mgr_ssn = mgr_ssn;
	}
	public String getMgr_start_date() {
		return mgr_start_date;
	}
	public void setMgr_start_date(String mgr_start_date) {
		this.mgr_start_date = mgr_start_date;
	}
	
	@Override
	public String toString() {
		return "Department [dname=" + dname + ", dnumber=" + dnumber + ", mgr_ssn=" + mgr_ssn + ", mgr_start_date="
				+ mgr_start_date + "]";
	}
}
