package inventory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
//Equipment class to store various properties of equipments
public class Equipment {
    private String equipmentName;
    private String equipmentId;
    private String location;
    private int count;
    public Equipment(String equipmentName,String equipmentId,String location,int count)
    {
        this.count=count;
        this.equipmentId=equipmentId;
        this.equipmentName=equipmentName;
        this.location=location;
    }
    public Equipment(String location,int count)
    {
        this.count=count;
        this.location=location;
    }
    //function to return fields
    public String getName()
    {
        return this.equipmentName;
    }
    public String getId()
    {
        return this.equipmentId;
    }
    public int getCount()
    {
        return this.count;
    }
    public String getLocation()
    {
        return this.location;
    }
    
}
