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

//stores student roll nuber and is sent to StudentRegistrationGUI for display
public class StudentRegistration extends SemesterRegistration{
    public String rollNumber;
    public int semesterNumber;
    public void registerStudent(){
        new StudentRegistrationGUI(this).setVisible(true);
    }
}
