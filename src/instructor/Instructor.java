package instructor;


import java.sql.*;
//import static java.sql.JDBCType.NULL;
import java.util.Date;
import java.text.SimpleDateFormat;  

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
//Instructor class which will display instructor information
public class Instructor {

    /**
     * @param args the command line arguments
     */
    private static final String rootUsername="root";
    private static final String rootPassword="password";
    private static final String conn_string="jdbc:mysql://localhost:3306/UDIS";
    private Connection conn=null;
    String instructorName;
    String address;
    String emailAddress;
    Date dateOfBirth;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    String strDate; 
    String mobileNumber;
    String courses="";
    String projects="";
    String publications="";
    int yearOfJoining;
    public int id;
    
    
    public void displayInstructorDetails()
    {
        try{
            conn= DriverManager.getConnection(conn_string, rootUsername, rootPassword);
            Statement st=(Statement) conn.createStatement();
            String query="SELECT * FROM instructors WHERE id='"+this.id+"' ";
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                this.instructorName=rs.getString("name");
                this.address=rs.getString("address");
                this.mobileNumber=rs.getString("mobileNumber");
                this.yearOfJoining=rs.getInt("yearOfJoining");
                this.emailAddress=rs.getString("email");
                this.dateOfBirth=rs.getDate("dateOfBirth");
                this.strDate=formatter.format(dateOfBirth);
            }
           query="SELECT * FROM courses  ORDER by year DESC";
           rs=st.executeQuery(query);
           query="SELECT * FROM courses WHERE instructorId=' "+this.id+"'";
           rs=st.executeQuery(query);
           while(rs.next())
           {
               if(rs.getInt("studentId")!=0)
               {
                   continue;
               }
               else
               {
                   
                   int k=rs.getInt("semester");
                   if(k==2)
                   {this.courses=this.courses+"\n"+rs.getString("courseId")+" "+rs.getString("name")+" "+Integer.toString(rs.getInt("year"))+" Autumn";}
                   if(k==1)
                   {
                      this.courses=this.courses+"\n"+rs.getString("courseId")+" "+rs.getString("name")+" "+Integer.toString(rs.getInt("year"))+" Spring";
                   }
               }
               
           }
           query="SELECT * FROM research ORDER by year DESC";
           rs=st.executeQuery(query);
           query="SELECT * FROM research WHERE instructorId=' "+this.id+" ' AND  researchType=' "+1+" '";
           rs=st.executeQuery(query);
           while(rs.next())
           {
               this.projects=this.projects+"\n"+rs.getString("name")+" "+Integer.toString(rs.getInt("year"));
           }
           query="SELECT * FROM research WHERE instructorId=' "+this.id+" ' AND  researchType=' "+2+" '";
           rs=st.executeQuery(query);
           while(rs.next())
           {
               this.publications=this.publications+"\n"+rs.getString("name")+" "+Integer.toString(rs.getInt("year"));
           }
              new InstructorGui(this).setVisible(true);
           }
            
            
           
            
        
        catch(SQLException e){
            System.err.println(e);
        }
                
                        
            
            
        
    }
}
   
    

