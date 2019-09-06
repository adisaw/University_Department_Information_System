package ds;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjot273
 */
//calls course registration gui to complete course registration
public class CourseRegistration {
    public String courseId;
    public String courseName;
    public String courseInstructor;
    public int courseCredits;
    
    //calls course registration gui to complete course registration
    public void registerCourse(){
        new CourseRegistrationGUI(this).setVisible(true);
    }
}