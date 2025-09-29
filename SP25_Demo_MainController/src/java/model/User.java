/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class User {
 private int id;
 private String username;
 private String email;
 private String country;
 private String role;
 private boolean status;
 private String password;
 private java.time.LocalDate dob;
 // Constructor mặc định
 public User() {
 }
 // Constructor đầy đủ

    public User(int id, String username, String email, String country, String role, boolean status, String password, LocalDate dob) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.country = country;
        this.role = role;
        this.status = status;
        this.password = password;
        this.dob = dob;
    }
public java.time.LocalDate getDob() { return dob; }
public void setDob(java.time.LocalDate dob) { this.dob = dob; }
 // Getter và Setter cho các thuộc tính
 public int getId() {
 return id;
 }
 public void setId(int id) {
 this.id = id;
 }
 public String getUsername() {
 return username;
 }
 public void setUsername(String username) {
 this.username = username;
 }
 public String getEmail() {
 return email;
 }
 public void setEmail(String email) {
 this.email = email;
 }
 public String getCountry() {
 return country;
 }
 public void setCountry(String country) {
 this.country = country;
}
  public String getRole() {
 return role;
 }
 public void setRole(String role) {
 this.role = role;
 }
 public boolean isStatus() {
 return status;
 }
 public void setStatus(boolean status) {
 this.status = status;
 }
 public String getPassword() {
 return password;
 }
 public void setPassword(String password) {
 this.password = password;
}
 //hương thức toString() để in đối tượng User
 @Override
 public String toString() {
 return "User{" +
 "id=" + id +
 ", username='" + username + '\'' +
 ", email='" + email + '\'' +
 ", country='" + country + '\'' +
 ", role='" + role + '\'' +
 ", status=" + status +
 ", password='" + password + '\'' +
 '}';
 }

 
}

