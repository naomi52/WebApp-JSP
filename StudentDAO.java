package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
	private static StudentDAO instance = null;
	public static final String DB_URL = "jdbc:derby://localhost:64413/EECS;user=student;password=secret"; 
	String query;
	
	public StudentDAO()
	{
	}

	public synchronized static StudentDAO getInstance()
	{
		if (instance == null) instance = new StudentDAO();
		return instance;
	}
	
	public List<StudentBean> retrieve(String name, String gpa, String orderVal) throws Exception {
		//System.out.println(String.valueOf(orderVal=="NONE"));
		//System.out.println("my gpa is: "+gpa);
		List<StudentBean> myList = new ArrayList<StudentBean>();
		
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection con = DriverManager.getConnection(DB_URL);
        Statement s = con.createStatement();
        s.executeUpdate("set schema roumani");
         
        //Double myGPA = Double.parseDouble(gpa);
        
        
        //String state = "SELECT SURNAME, GIVENNAME, MAJOR, COURSES, GPA  FROM SIS WHERE UPPER(SURNAME) LIKE UPPER(?) AND GPA>= ? ORDER BY ";
        String stmt = "SELECT SURNAME, GIVENNAME, GPA, COURSES, MAJOR FROM SIS WHERE (UPPER(SURNAME) "
        		+ "LIKE UPPER(?) OR TRUE="+String.valueOf(name=="")+")";	// AND GPA>= ?";
        String orderBy =  " ORDER BY ";
        String myGPA = " AND (GPA>= ";
        
        if((orderVal.equals("NONE") && gpa.equals(""))) {	//I have none
        	query = stmt; 
        	System.out.println(query);
        }
        else if(!(gpa.equals("")) && (orderVal.equals("NONE"))) {	//if i have a gpa and not an orerVal then do this
        	 query = stmt + myGPA + gpa + ")";
        	 System.out.println(query);
        	//prepared.setString(2, gpa);
        }
        else if(!(orderVal.equals("NONE")) && (gpa.equals(""))){	//if i have an order val but not a gpa
        	query = stmt + orderBy + orderVal;
        	//System.out.println(query);
        }
        else {	//I have both
        	query = stmt + myGPA + gpa + ")" +orderBy + orderVal;
        	System.out.println(query);
        }
        
        
        String userName = name+"%";
        PreparedStatement prepared = con.prepareStatement(query);
    	prepared.setString(1, userName);
    	//prepared.setString(2, gpa);
    	//prepared.setString(3, orderVal);
    	
    	
    	
        ResultSet r = prepared.executeQuery();
        String result = "";
       	while (r.next())
        {
        	result = r.getString("SURNAME") + " - " +r.getString("GIVENNAME") + r.getString("MAJOR")
        	+ r.getInt("COURSES") +" - "+ r.getDouble("GPA");
        	//StudentBean myBean = new StudentBean(mySur, myGiven, myMajor, myCourses, myGPA);
        	myList.add(new StudentBean(r.getString("SURNAME") + ", " + r.getString("GIVENNAME"), r.getString("MAJOR")
        			, r.getInt("COURSES"), r.getDouble("GPA")));
        }
        /*else
        {
        	throw new Exception(name + " not found!");
        }*/
       	
        r.close(); s.close(); con.close();
        
        for(int i=0; i<myList.size(); i++) {
        	StudentBean myBean = myList.get(i);
        }
        
        return myList;
        	
        }

}
