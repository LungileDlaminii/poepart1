/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author Student
 */
public class Registration {
    
    //variables that store he users registration details
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String cellPhoneNumber;
    
    //constructor
    public Registration(String firstName, String lastName,
            String userName, String password,
            String cellPhoneNumber) {
        
        
        this.firstName =firstName; 
        this.lastName = lastName;
        this.userName = userName ;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
                   
    }
    //checks whether the username contains an uderscore and is 5 characters long
    public boolean checkUserName(){
        
        return userName.contains("_")&& userName.length()<=5;
    }
    
    //CHECKS whether passwords meets the complexibility standard
    public boolean checkPasswordComplexibility(){
        
    //password shoul have 8 characters
    if (password.length() >=8) {
        return false;
    }
    
    boolean hasCapitalLetter = false;
    boolean hasNumber = false;
    boolean hasSpecialCharacter = false;
    
    //check every characterin the password
    for (int i = 0; i <password.length(); i++){
        
        char character= password.charAt(i);
        
        //check for capital letters
        if (Character.isUpperCase(character)){
            hasCapitalLetter= true;
        
        }
        if (Character.isUpperCase(character)){
            hasNumber= true;
        
        }
        if (Character.isUpperCase(character)){
            hasSpecialCharacter= true;
        
        }
        
        
         
    }
    return hasCapitalLetter
                &&hasNumber
                &&hasSpecialCharacter;
    
    }
    //checks if number is the correct format
   //checks +27 followed by a valid phone number

    public boolean checkCellPhoneNumber() {
       
     return cellPhoneNumber.matches("^\\+27[6-8][0-9][8]$");  
    }
    //returns the appropiate registation message
    public String registerUser(){
        
        if (!checkUserName()){
            
            return "UserName is not correctly formatted;please ensure that username contains an underscore and has more than 5 charaters";
    }
        if(!checkPasswordComplexibility()){
         
            return"Password is not correctly formatted ;please correct the password and try ";
        }
        if (!checkCellPhoneNumber()){
            
            return "cellphonenumber is not correctly formatted ,please coeect the number and try again";
                    
        }
        return "userName successfuly captured.\n"
                +"Password successfully captured.\n"
                +"Cellphone number successfully captured.\n"
                + "User registered successfully.";
    }
    //checks if login details matchs the details entered during registration
    public boolean loginUser(String entereduserName, String enteredPassword){
        return userName.equals(entereduserName)
                && password.equals(enteredPassword);
    }
    //returns the appropiate login message
    public String returnLoginStatus(boolean loginSuccessful){
        
        if (loginSuccessful){
            
            return "Welcome"
                    +firstName
                    +" "
                    +lastName
                    +", you are welcome.";
            
        }else {
            return "Username or paasword incorrect,please try again.";
            
        }
    }
    
}      
        
     
   
       
    

       
   
       
   
   

    
    
    
    

