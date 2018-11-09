package analytics;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Monitor
 *
 */
@WebListener
public class Monitor implements ServletRequestAttributeListener {
	
	private static int totalUsage = 0;
    private static int droneUsed = 0;
    private static int rideUsed = 0;

    /**
     * Default constructor. 
     */
    public Monitor() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent srae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent srae)  { 
    	String servName = srae.getServletRequest().getParameter("servName");
        String paramName = srae.getName();
         

         
        if(servName != null) {
            if(servName.equals("Drone") && paramName.equals("result")) {
                droneUsed++;
                totalUsage++;
                //log("Drone used " + getDroneUsage() + " times. " + percentDrone());
            }
            else if(servName.equals("Ride") && paramName.equals("result")) {
            	rideUsed++;
                totalUsage++;
            }
            else if (!servName.equals("Drone") && paramName.equals("result")) {
                totalUsage++;
                //log("other service used (" + servName + ")");
            }
        } 
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
         // TODO Auto-generated method stub
    }
    
    public static int getDroneUsage() {
    	
        return droneUsed;
    }
    
    public static String percentDrone() {
        double perc = 100 * (double)droneUsed / (double)totalUsage;
        //System.out.println(Double.toString(perc));
        if(Double.toString(perc) == "NaN") {
        	return ("0%");
        }
        else {
        	return String.format("%.1f", perc) + "%"; 
        }
        
    }
    
    
    public static String percentRide() {
        double perc = 100 * (double)droneUsed / (double)totalUsage;
        return String.format("%.1f", perc) + "%";
    
    }
    
    
    public static String probS3ANDS4() {
         
        double pS3 = (double)droneUsed / (double)totalUsage;
        double pS4 = (double)rideUsed / (double)totalUsage;
         
        double p = pS3 * pS4;
        
        if(Double.toString(p) == "NaN") {
        	return ("0% chance");
        }
        else {
        	return String.format("%.1f", p) + "% chance.";
        }
        
         
    } 
	
}
