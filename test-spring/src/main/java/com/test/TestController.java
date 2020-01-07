package com.test;

import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@RestController
public class TestController{
	
	@Qualifier("jdbcthinkService")
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/hello")
	public String hello() throws UnknownHostException {
		
		String systemipaddress = ""; 
        try { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = new BufferedReader( 
                new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (UnknownHostException e) { 
            systemipaddress = "Cannot Execute Properly"; 
        } 
		
		return "welcome "+systemipaddress;
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showLogin1(HttpServletRequest request, HttpServletResponse response) {
		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		System.out.println("username:inside login"+n);
		System.out.println("password:inside login"+p);
		// LoginService us = new LoginService();
		User user = loginService.validdateUser(n, p);
		String hostname = null;

		if (null != user) {

			System.out.println("welcome:" + n);
			
			InetAddress ip;
	        
	        try {
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostName();
	            System.out.println("Your current IP address : " + ip);
	            System.out.println("Your current Hostname : " + hostname);
	 
	        } catch (UnknownHostException e) {
	 
	            e.printStackTrace();
	        }
	  
			
		} else {
			
			System.out.println("please register");

		}

		System.out.println(n);
		System.out.println(p);

		return "welcome "+"\n "+hostname;
		
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
	 public int register(HttpServletRequest request , HttpServletResponse response) throws ParseException {
		 
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
		 
		return row;
		 
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
