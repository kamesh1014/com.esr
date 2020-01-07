package com.test;

public class User {

  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String address;
  private String phone;
  private String dob;

  public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    System.out.println("username: " + username);
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    System.out.println("firstname: " + firstname);
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    System.out.println("lastname: " + lastname);
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
	  System.out.println("lastname: " + email);
    this.email = email;
  }

  public String getAddress() {
	  
    return address;
  }

  public void setAddress(String address) {
	  System.out.println("lastname: " + address);
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
	  System.out.println("phone: " + phone);
    this.phone = phone;
  }
}
