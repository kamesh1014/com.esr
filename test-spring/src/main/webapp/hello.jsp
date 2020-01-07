<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<style>
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer; 
}

.button1 {
  background-color: white; 
  color: black; 
  /* border: 2px solid #4CAF50; */
    position: absolute;
    top: 50%;
    right: 20%;
}

.button1:hover {
  background-color: #4CAF50;
  color: white;
  
}

.button2 {
  background-color: white; 
  color: black; 
  /* border: 2px solid #008CBA; */
    position: absolute;
    top: 50%;
    right: 70%;
}

.button2:hover {
  background-color: #008CBA;
  color: white;
}

body, html {
  height: 100%;
  margin: 0;
}

.bg {
  /* The image used */
 /*  background-image: url("resources/images/crop.jpg"); */
  backgroung-colour: white;

  /* Full height */
  height: 100%;  

  /* Center and scale the image nicely */
   background-position: center;
  background-repeat: no-repeat;
   /* background-size: cover;  */
}
</style>

<body class ="bg">


	<table align="center">
	<!-- <tr  style="width:20%;font-size:50px;color:black" align="center" >
			<td>Why so serious</td>
		</tr> -->
		<tr align="center">
			<!--  <td><a href="login">Login</a></td>
			<td><a href="register">Register</a></td>  -->
			<td><a href="login">
               <button class="button button1">Login</button>
             </a></td>
			<td><a href="register">
			<button class="button button2">Register</button>
			</a></td>
		</tr>
	</table>
	<!-- <img src="resources/images/download.jpg" /> -->
</body>
</html>