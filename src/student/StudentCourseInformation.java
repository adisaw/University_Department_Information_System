package student;


import ds.EnterGradesGUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjot273
 */
public class StudentCourseInformation {
    public String rollNumber;
    public String name;
    public float CGPA;
    public float SGPA;
    private Student student;
    private static final String rootUsername="root";
    private static final String rootPassword="password";
    private static final String conn_string="jdbc:mysql://localhost:3306/UDIS";
    private Connection conn=null;
    
    public float getGrade(String s){
        if(s.equals("EX"))
            return 10.0f;
        else if(s.equals("A"))
            return 9.0f;
        else if(s.equals("B"))
            return 8.0f;
        else if(s.equals("C"))
            return 7.0f;
        else if(s.equals("D"))
            return 6.0f;
        else if(s.equals("P"))
            return 5.0f;
        else
            return 0.0f;
    }
    
    public StudentCourseInformation(){
        
    }
    
    public StudentCourseInformation(Student student){//constructor,sets the field student
        this.student=student;
    }
    
    public void displayCourseDetails(boolean flag){
        new DisplayCourseDetailsGUI(student,flag).setVisible(true);//calls DisplayCourseDetailsGUI
    }
    
    public void calculateGPA(){//CALCULATES the GPA of the student and updates in database
        //done in GradeSheetGUI.java
        this.student=student;
        try
        {
            CGPA=0.0f;
            float sumCredits=0.0f;
            float weightedSum=0.0f;
            conn= DriverManager.getConnection(conn_string, rootUsername, rootPassword);
            Statement st=(Statement) conn.createStatement();
            String query;
            ResultSet rs;
            query="SELECT * FROM students where rollNumber='"+student.rollNumber+"'";
            rs=st.executeQuery(query);
            rs.next();
            int studentId=rs.getInt("id");
            query="SELECT * FROM courses WHERE studentId='"+studentId+"' AND grade IS NOT NULL";
            rs=st.executeQuery(query);
            if(!rs.next()){
            }else{
                do{
                    sumCredits+=rs.getInt("credits");
                    weightedSum+=rs.getInt("credits")*getGrade(rs.getString("grade"));
                }while(rs.next());
            }
            if(sumCredits!=0.0f){
               CGPA=weightedSum/sumCredits;
            }
            query="UPDATE students SET CGPA='"+CGPA+"' WHERE rollNumber='"+student.rollNumber+"'";
            st.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.err.println(e); 
        }
    }
    
    public void enterGrades(){//display entergrades gui
        new EnterGradesGUI().setVisible(true);
    }
    
    public void printGradeSheet(boolean flag){//displays gradesheet
        new GradeSheetGUI(this.student,flag).setVisible(true);
    }
}
