package accounts;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aditya
 */
//Transaction class to store details of any transaction
public class Transaction {
    
    private String purpose;
    private float amount;
    private Date date;
    private String accountHolder;
    private String accountNumber;
    private int type;
    private String ttype;
    
    public Transaction(String purpose,float amount,Date date,String accountHolder,String accountNumber,int type)
    {
        this.purpose=purpose;
        this.accountHolder=accountHolder;
        this.accountNumber=accountNumber;
        this.amount=amount;
        this.type=type;
        if(type==1)
        {
            this.ttype="Credit";
        }
        if(type==2)
        {
            this.ttype="Debit";
        }
        this.date=date;
    }
    //some functions returning fields
    public Date getDate()
    {
        return this.date;
    }
    public String getPurpose()
    {
        return this.purpose;
    }
     public String getAccountHolder()
    {
        return this.accountHolder;
    }
    public String getAccountNumber()
    {
        return this.accountNumber;
    }
    public String getType()
    {
        return this.ttype;
        
    }
    public float getAmount()
    {
        return this.amount;
    }
}
    
    

