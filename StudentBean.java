package model;

public class StudentBean {
	
	//private String surname;
	//private String givenname;
	private String name;
	private String major;
	private int courses;
	private double gpa;
	
	public StudentBean(String name, String major, int courses, double gpa) {
		//super();
		//this.surname = surname;
		//this.givenname = givenname;
		this.name = name;
		this.major = major;
		this.courses = courses;
		this.gpa = gpa;
	}
	
	
	/*public String getsurName() {
		return surname;
	}
	public void setsurName(String surname) {
		this.surname = surname;
	}
	public String getgivenName() {
		return givenname;
	}
	public void setgivenName(String givenname) {
		this.givenname = givenname;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getCourses() {
		return courses;
	}
	public void setCourses(int courses) {
		this.courses = courses;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	@Override
	public String toString() {
		return (name + " " + major + " "+ courses + " " + gpa);
	}
	
	

}
