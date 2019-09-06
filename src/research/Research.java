package research;


import research.DeleteResearch;
import research.AddResearch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
//class Research to store various properties related to Research
public class Research {
    
    
    String instructor;
    String type;
    int year; 
    String name;
    
    /**
     * @param args the command line arguments
     */
    //function to add research information
    public void add()
    {
         new AddResearch().setVisible(true);
    }
    //function to delete research information
    public void delete()
    {
        new DeleteResearch().setVisible(true);
    }
    
    public static void main(String[] args) {
        Research r1=new Research();
        r1.delete();
        // TODO code application logic here
    }
    
}
