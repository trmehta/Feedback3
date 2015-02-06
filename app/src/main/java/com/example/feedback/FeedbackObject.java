package com.example.feedback;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by trmehta on 1/23/2015.
 */
public class FeedbackObject {

    //private variables
    //int id=0;
    String username;
    String contact= "000000";
    String subcategory = "xyz";
    int star=5;
    String cookie="cookies";
    String time;
    String description;
    String category;
    byte[] image;

    // Empty constructor
    public FeedbackObject(){

    }
    // constructor
    public FeedbackObject(String username, String description, String category, byte[] image){
       //this.id = id++;
        this.username = username;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    public  FeedbackObject(String username, String description, String category)
    {
        this.username = username;
        this.contact = "0000000";
        this.category = category;
        this.subcategory = "xyz";
        this.description = description;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        time = sdf.format(new Date());
    }

    public  FeedbackObject(String category, String contact, String cookie, String description, String subcategory, String time, String username, int star)
    {
        this.username = username;
        this.contact = contact;
        this.category = category;
        this.subcategory = subcategory;
        this.cookie = cookie;
        this.description = description;
        this.time = time;
        this.star = star;
    }

    public FeedbackObject(int id, String username, String description, String category, byte[] image){
        //this.id = id;
        this.username = username;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    // getting ID
   /*public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }*/

    // getting username
    public String getUsername(){
        return this.username;
    }
    public String getContact(){
        return this.contact;
    }
    public int getStar(){
        return this.star;
    }

    // setting username
    public void setUsername(String username){
        this.username = username;
    }

    // getting description
    public String getDescription(){
        return this.description;
    }

    // setting description
    public void setDescription(String description){
        this.description = description;
    }

    // getting category
    public String getCategory(){
        return this.category;
    }

    // setting category
    public void setCategory(String category){
        this.category = category;
    }

    public byte[] getImage(){ return this.image; }

    public void setImage(byte[] image){this.image = image;}
}
