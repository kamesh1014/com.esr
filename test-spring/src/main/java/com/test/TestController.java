package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

@Controller
public class TestController {
	
	@Qualifier("jdbcthinkService")
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/hello")
	public String hello() {
		String systemipaddress = ""; 
        try { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = new BufferedReader( 
                new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (Exception e) { 
            systemipaddress = "Cannot Execute Properly"; 
        } 
        // Print IP address 
        System.out.println("Public IP Address: "
                           + systemipaddress + "\n"); 	
		return "welcome";
	}
	
	@RequestMapping("/ipaddress")
	public String ip(Model model) {
		String systemipaddress = ""; 
        systemipaddress = getIPAddress();
        String systemName = getSystemName();
        String systemMac = getMAC();
        
        System.out.println("systemipaddress:"+systemipaddress);
        System.out.println("systemName:"+systemName);
        System.out.println("systemMac:"+systemMac);
        
        model.addAttribute("ipaddress", systemipaddress);
        model.addAttribute("systemName", systemName);
        model.addAttribute("systemMac", systemMac);
        
		return "ipaddress";
	}
	
	@RequestMapping("/findip")
	public String findip(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		
		String ip = request.getParameter("ip");
		System.out.println("find ip address:"+ip);
		String region = location(ip);

      //  model.addAttribute("systemMac", );
		model.addAttribute("region",region);
		return "region";
	}
	@RequestMapping("/map")
	public String map(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		
		return "map";
	}
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	   public String redirect() {
	      return "redirect:finalPage";
	   }
	   @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
	   public String finalPage() {
	      return "final";
	   }
	
	
	@RequestMapping("/location")
	public String ipRegion(Model model) throws IOException {
		
		String region = location(getIPAddress());
		model.addAttribute("region",region);
		return "region";
		
		
	}
	
	 private static String getSystemName(){  
	        try{
	            InetAddress inetaddress=InetAddress.getLocalHost(); //Get LocalHost refrence
	            String name = inetaddress.getHostName();   //Get Host Name
	            return name;   //return Host Name
	        }
	        catch(Exception E){
	            E.printStackTrace();  //print Exception StackTrace
	            return null;
	        }
	    }
	     
	    /**
	     * method to get Host IP
	     * @return Host IP Address
	     */
	    private static String getIPAddress(){
	         try{
	            InetAddress inetaddress=InetAddress.getLocalHost();  //Get LocalHost refrence
	            String ip = inetaddress.getHostAddress();  // Get Host IP Address
	            return ip;   // return IP Address
	        }
	        catch(Exception E){
	            E.printStackTrace();  //print Exception StackTrace
	            return null;
	        }
	         
	    }
	     
	    /**
	     * method to get Host Mac Address
	     * @return  Mac Address
	     */
	    private static String getMAC(){
	         try{
	            InetAddress inetaddress=InetAddress.getLocalHost(); //Get LocalHost refrence
	             
	            //get Network interface Refrence by InetAddress Refrence
	            NetworkInterface network = NetworkInterface.getByInetAddress(inetaddress); 
	            byte[] macArray = network.getHardwareAddress();  //get Harware address Array
	            StringBuilder str = new StringBuilder();
	             
	            // Convert Array to String 
	            for (int i = 0; i < macArray.length; i++) {
	                    str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
	            }
	            String macAddress=str.toString();
	         
	            return macAddress; //return MAc Address
	        }
	        catch(Exception E){
	            E.printStackTrace();  //print Exception StackTrace
	            return null;
	        } 
	    }
	
	    
	    public String location(String ip) throws IOException {
	    	
	    	File dbfile = new File("C:\\Users\\580185\\Desktop\\GeoLiteCity.dat");
			
			System.out.println(dbfile.getAbsolutePath());
			String region = null;
			
			LookupService lookupService = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);
			//String ip = getIPAddress();
			Location location = lookupService.getLocation(ip);

			// Populate region. Note that regionName is a MaxMind class, not an instance variable
			if (location != null) {
			    location.region = regionName.regionNameByCode(location.countryCode, location.region);
			    float a = location.latitude;
				float b = location.longitude;
				
				System.out.println(a);
				System.out.println(b);
				System.out.println(location.region);
				
				region = location.region;
			
			}
			else {
				region = "Could not find region";
			}

			
	    	
			return region;
	    	
	    	
	    	
	    }
	    
	    @ResponseBody 
	    @RequestMapping(value = "/search/api/getSearchResult/{latitude}/{longitude}") 
	    public String getSearchResultViaAjax(@PathVariable(value = "latitude") String latitude,@PathVariable(value = "longitude") String longitude) 
	    { 
	    	
	    	System.out.println("lat"+latitude);
	    	System.out.println("long"+longitude);
	    	
	    	String latlang = latitude +"/"+longitude;
	    	Mailer mail = new Mailer();
			mail.send("kam191095@gmail.com", "ravijayakamesh", "kam191095@gmail.com", "kk", latlang);
	    	return null;
	    // return String.valueOf(id); 
	    } 
	    
	/*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
    }*/

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showLogin1(HttpServletRequest request, HttpServletResponse response,Model model) {
		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		System.out.println("username:inside login"+n);
		System.out.println("password:inside login"+p);
		User user = loginService.validdateUser(n, p);

		if (null != user) {
			System.out.println("welcome:" + n);
		} else {
			
			System.out.println("please register");

		}
	//	redir.addFlashAttribute("firstname",n);
		model.addAttribute("firstname", n);
		System.out.println(n);
		System.out.println(p);

		return "welcome";
		
	}
	
	@Scheduled(fixedRate = 1000)
	@RequestMapping("/track")
	public String track() throws IOException {
		
		System.out.println("start map");
		
		return "map";
	}
	
	@RequestMapping(value = "/login1", method = RequestMethod.POST)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		System.out.println("username:inside login"+n);
		System.out.println("password:inside login"+p);
		ModelAndView mav = new ModelAndView();
		// LoginService us = new LoginService();
		User user = loginService.validdateUser(n, p);
		mav.setViewName("show");
		
		if (null != user) {

			System.out.println("welcome:" + n);
		//	mav = new ModelAndView("welcome");
		//	mav.setViewName("welcome");
			mav.addObject("firstname", user.getFirstname());
		} else {
			System.out.println("please register");

		}

		System.out.println(n);
		System.out.println(p);

		return mav;
	}
	 
	 @RequestMapping(value="/register" , method=RequestMethod.POST)
	 public String register(HttpServletRequest request , HttpServletResponse response) throws ParseException {
		 
		 String firstName = request.getParameter("firstName");
		 String lastName = request.getParameter("lastName");
		 String dob = request.getParameter("birthdate");
		 String address = request.getParameter("address");
		 String userName = request.getParameter("userName");
		 String password = request.getParameter("password");
		// int phone = Integer.parseInt(request.getParameter("phone"));
		 
		 String phone = request.getParameter("phone");
		 String email = request.getParameter("email");
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
		 
		  Date date=  sdf.parse(dob);
		 
		  String query ="INSERT INTO registration (firstname, lastname, dob, address, username, password, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		  
		  int row = jdbcTemplate.update(query,firstName,lastName,dob,address,userName,password,phone,email);
		 
		return "registrationsuccess";
		 
	 }
	 
	@RequestMapping("/new")
	public int think() {
		System.out.println("inseide new ");
		String query ="INSERT INTO THINK ( "+ 
	"id,"+
	"index,"+
	"VALUES(?,?)";
		
		String query1 ="INSERT INTO think (id, idea) VALUES (?, ?)";
		
		int row = jdbcTemplate.update(query1,1,"kamesh");
		
		System.out.println("row inserted "+row);
		
		return row;
		
	}
	
	
	

}
