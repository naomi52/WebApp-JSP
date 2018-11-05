package model;

import java.math.BigInteger;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import analytics.Monitor;

import java.lang.Math;


public class Engine
{
	public static final String DRONE_URL = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
	public static final String API_KEY = "&key=AIzaSyByA6lVf54NwliIeaMSBUQZzfOeRcs-CJw";
	
	public static final String FIRST_RIDE_URL = "https://maps.googleapis.com/maps/api/distancematrix/xml?origins=";
	public static final String SECOND_RIDE_URL = "&destinations=";
	public static final String THIRD_RIDE_URL = "&departure_time=now";
	
	
	private static Engine instance = null;
	private static final int GPS_VAL = 12742;
	private BigInteger comparison = new BigInteger("0");
	public StudentDAO myDAO;
	
	public Engine()
	{
		myDAO = StudentDAO.getInstance();
	}

	public synchronized static Engine getInstance()
	{
		if (instance == null) instance = new Engine();
		return instance;
	}
	
	public BigInteger doPrime(String lower, String upper) throws Exception
	{
		int intUpper = Integer.parseInt(upper);
		int intComparison = comparison.intValue();
		//System.out.println(intRandomPrime);
		if (intUpper<(intComparison)) throw new Exception ("No more primes in this range");
		
		if(Integer.parseInt(upper)<Integer.parseInt(lower)) throw new Exception ("Invalid Range");
		
		try
		{
			BigInteger lowerInt = new BigInteger(lower); 
			//BigInteger upperInt = new BigInteger(upper);
			BigInteger randomPrime = lowerInt.nextProbablePrime();
			comparison = randomPrime.nextProbablePrime();
			
			return randomPrime;
		} 
		catch (Exception e)
		{
			throw new Exception("The entry must be int!");
		}
		
		
	}
	
	public int doGps(String latFrom, String longFrom, String latTo, String longTo) throws Exception {
		double mylatFrom = Double.parseDouble(latFrom) * (Math.PI/180);
		//System.out.println(mylatFrom);
		double mylatTo = Double.parseDouble(latTo) * (Math.PI/180);
		
		double mylongFrom = Double.parseDouble(longFrom) * (Math.PI/180);
		double mylongTo = Double.parseDouble(longTo) * (Math.PI/180);
		//System.out.println(mylongFrom);
		
		double y = (Math.cos(mylatFrom)*Math.cos(mylatTo));
		
		double x = Math.pow((Math.sin((mylatTo-mylatFrom)/2)), 2) + y *  Math.pow((Math.sin((mylongTo-mylongFrom)/2)), 2);
		
		try {
			int myAns = (int) (GPS_VAL * Math.atan2(Math.sqrt(x),Math.sqrt(1-x)));
			return myAns;
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}
	
	public int doDrone(String add1, String add2) throws Exception {
		String myfirstAdd = add1.replaceAll(" ", "+");
		String mysecondAdd = add2.replaceAll(" ", "+");
		
		URL myfirstURL = new URL( DRONE_URL + myfirstAdd+ API_KEY );
		URL mysecondURL = new URL( DRONE_URL + mysecondAdd+ API_KEY );
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document docFirstURL = db.parse(myfirstURL.openStream());
		
		Document docSecondURL = db.parse(mysecondURL.openStream());
		
		String myFirstAddLat = docFirstURL.getElementsByTagName("lat").item(0).getTextContent();
		String myFirstAddLong = docFirstURL.getElementsByTagName("lng").item(0).getTextContent();
		
		String mySecondAddLat = docSecondURL.getElementsByTagName("lat").item(0).getTextContent();
		String mySecondAddLong = docSecondURL.getElementsByTagName("lng").item(0).getTextContent();
		/*
		System.out.println(docFirstURL.getElementsByTagName("lat").item(0).getTextContent());
		System.out.println(docSecondURL.getElementsByTagName("lat").item(0).getTextContent());
		System.out.println(docFirstURL.getElementsByTagName("lng").item(0).getTextContent());
		System.out.println(docSecondURL.getElementsByTagName("lng").item(0).getTextContent());*/
		
		try {
			int myAns = doGps(myFirstAddLat, myFirstAddLong, mySecondAddLat, mySecondAddLong);
			double finalAns= (myAns/(double)150)*(double)60;
			System.out.println(finalAns);
			return (int)finalAns;
			//return myAns;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public double doRide(String add1, String add2) throws Exception {
		String myfirstAdd = add1.replaceAll(" ", "+");
		String mysecondAdd = add2.replaceAll(" ", "+");
		
		URL myURL = new URL( FIRST_RIDE_URL + myfirstAdd + SECOND_RIDE_URL+ mysecondAdd + THIRD_RIDE_URL +  API_KEY );
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document docURL = db.parse(myURL.openStream());
		/*
		Element rootElement = docURL.getDocumentElement();
		NodeList children = rootElement.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for(int i=0; i<count; i++) {
			current = children.item(i);
			if(current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;
				myVal = getValue("text", element);
			}
		}
		System.out.println("hi");
		System.out.println(myVal);*/
		
		String mySecs= docURL.getElementsByTagName("value").item(2).getTextContent();
		double myAns = (((Double.parseDouble(mySecs)/60)*50.0)/100.0);
		double myRoundAns = Math.round(myAns * 100.0)/100.0;
		//System.out.println(myURL);
		return myRoundAns;
	}
	public String analyze(String option) throws Exception {
	       
        //ServletRequestAttributeListener lis = Monitor.instance();
        String result = "";
       
        if(option.equals("droneUsage")) {
            result = Monitor.getDroneUsage()+" times used";
        }
        else if(option.equals("dronePercent")) {
            result = Monitor.percentDrone();
        }
        else if(option.equals("drnRidProb")) {
            result = Monitor.probS3ANDS4();
            //throw new Exception("Work in progress. Currently Unavailable.");
        }
        else {
            throw new Exception("No option selected");
        }
       
            return result;
    }
	
	/*private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}*/
	
	
}
