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

import ds.RegisterNewStudentGui;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class DepartmentSecretary {
    public String name="Department Secretary";
    public String departmentName="Computer Science And Engineering";
    public int semester;
    public int ongoingYear;
    private static final String rootUsername="root";
    private static final String rootPassword="password";
    private static final String conn_string="jdbc:mysql://localhost:3306/UDIS";
    private Connection conn=null;
    
    public DepartmentSecretary(){
    }
    
    public void showOptions(){//shows the options available to the departemnt secretary
        new DepartmentSecretaryGui().setVisible(true);
    }
    
    public void registerNewStudent(){//calls registernewstudent gui
        new RegisterNewStudentGui().setVisible(true);
    }
    
}