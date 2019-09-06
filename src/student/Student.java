package student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjot273
 */
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class Student {
    public String rollNumber;
    public String name;
    public int ongoingSemester;
    public String emailAddress;
    public String hallName;
    public Date dateOfBirth;
    public String mobileNumber;
    public String address;
    public float CGPA;
    public int yearOfJoining;
    
    private static final String rootUsername="root";
    private static final String rootPassword="password";
    private static final String conn_string="jdbc:mysql://localhost:3306/UDIS";
    private Connection conn=null;
    
    public Student(){
    }
    
    public void set(String rollNumber){
        this.conn=null;
        try{
            conn= DriverManager.getConnection(conn_string, rootUsername, rootPassword);
            Statement st=(Statement) conn.createStatement();
            String query="SELECT * FROM students WHERE rollNumber='"+rollNumber+"'";
            ResultSet rs=st.executeQuery(query);
            if(!rs.next()){
                //Student not present
            }else{
                do{
                    System.out.println(rs.getString("username")+" "+rs.getString("hall"));
                }while(rs.next());
            }
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    
    public void displayStudentInfo(boolean flag){
        new StudentGui(this,flag).setVisible(true);//calls the Student GUI class
        
    }
}
