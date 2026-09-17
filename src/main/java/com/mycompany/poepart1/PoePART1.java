/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;

/**
 *
 * @author Student
 */
import java.util.Scanner;
public class PoePART1 {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       
        System.out.println("REGISTRATION AND LOGIN");
        
        System.out.println("Enter your first name");
        String firstName  = scanner.nextLine();
        
        System.out.println("Enter your Last name");
        String lastName  = scanner.nextLine();
        
        System.out.println("Enter a username");
        String userName  = scanner.nextLine();
        
        System.out.println("Enter a password");
        String password  = scanner.nextLine();
        
        System.out.println("Enter your South african cellphone number");
        String cellPhoneNumber  = scanner.nextLine();
        
        //registration object
        
        Registration registration = new Registration (
                firstName,
                lastName,
                userName,
                password,
                cellPhoneNumber   
         );
        //register the user
        String registrationMessage = registration.registerUser();
        
        System.out.println();
        System.out.println("registrationMessage");
        
        //only continue to login if registration was sucessful
        
        if(registration.checkUserName()
                && registration.checkPasswordComplexibility ()
                && registration.checkCellPhoneNumber ()){
            
            System.out.println();
            System.out.println("LOGIN");
            
            System.out.println("enter your username : ");
            String loginUsername = scanner.nextLine();
            
            System.out.println("enter your password: ");
            String loginPassword = scanner.nextLine();
            
            boolean loginSuccessful = registration.loginUser(
                    loginUsername,
                    loginPassword);
            
            System.out.println();
            System.out.println(
                    registration.returnLoginStatus(loginSuccessful)
            );
            
         scanner.close();
        
        
    }
}
}
    
