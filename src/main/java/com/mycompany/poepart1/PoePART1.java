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
       
        System.out.println("REGISTRATION ");
        
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        
        System.out.println("Enter your Last name");
        String lastName = scanner.nextLine();
        
        System.out.println("Enter a username");
        String userName = scanner.nextLine();
        
        System.out.println("Enter a password");
        String password = scanner.nextLine();
        
        System.out.println("Enter your South African cellphone number");
        String cellPhoneNumber = scanner.nextLine();
        
        Registration registration = new Registration(
                firstName,
                lastName,
                userName,
                password,
                cellPhoneNumber   
        );
        //register the user
        
        String registrationMessage = registration.registerUser();
        System.out.println();
        System.out.println(registrationMessage);
        
        if (registration.checkUserName()
                && registration.checkPasswordComplexibility()
                && registration.checkCellPhoneNumber()) {
            
        if(registration.checkUserName()
                && registration.checkPasswordComplexibility ()
                && registration.checkCellPhoneNumber ()){
            
            System.out.println();
            System.out.println("LOGIN");
            
            System.out.println("Enter your username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.println("Enter your password: ");
            String loginPassword = scanner.nextLine();
            
            boolean loginSuccessful = registration.loginUser(
                    loginUsername,
                    loginPassword);
            
            System.out.println();
            System.out.println(registration.returnLoginStatus(loginSuccessful));
            
            // QUICKCHAT MENU LOOP
            if (loginSuccessful) {
                boolean running = true;
                
                while (running) {
                    System.out.println("\nWelcome to QuickChat.");
                    System.out.println("Please choose one of the following features:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Enter choice (1, 2, or 3): ");
                    
                    String choice = scanner.nextLine();
                    
                    switch (choice) {
                        case "1":
                            System.out.print("How many messages would you like to enter? ");
                            int totalMessagesToEnter = 0;
                            try {
                                totalMessagesToEnter = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format. Returning to menu.");
                                break;
                            }
                            
                            int successfullySentCount = 0;
                            
                            for (int i = 1; i <= totalMessagesToEnter; i++) {
                                System.out.println("\n--- Message " + i + " of " + totalMessagesToEnter + " ---");
                                
                                System.out.print("Enter recipient cell phone number: ");
                                String recipientNum = scanner.nextLine();
                                
                                System.out.print("Enter message (max 250 characters): ");
                                String msgContent = scanner.nextLine();
                                
                                Message msg = new Message(recipientNum, msgContent);
                                
                                // Validate Cell
                                String cellCheck = msg.checkRecipientCell();
                                System.out.println(cellCheck);
                                if (!cellCheck.contains("successfully captured")) {
                                    i--;
                                    continue;
                                }
                                
                                // Validate Length
                                String lengthCheck = msg.checkMessageLength();
                                System.out.println(lengthCheck);
                                if (!lengthCheck.contains("Message ready to send")) {
                                    i--;
                                    continue;
                                }
                                
                                // Sub-menu options
                                System.out.println("\nChoose an option:");
                                System.out.println("- Send Message (Press Enter or type 'send')");
                                System.out.println("- Disregard Message (Press 0 to delete)");
                                System.out.println("- Store Message to send later (Type 'store')");
                                System.out.print("Your choice: ");
                                String subChoice = scanner.nextLine().trim();
                                
                                String result = msg.sentMessage(subChoice);
                                System.out.println("\n" + result);
                                
                                if (!subChoice.equals("0") && !subChoice.equalsIgnoreCase("store")) {
                                    successfullySentCount++;
                                } else if (subChoice.equals("0")) {
                                    i--; // Repeat loop iteration for deleted message slot
                                }
                            }
                            
                            System.out.println("\nTotal number of messages successfully sent: " + successfullySentCount);
                            break;
                            
                        case "2":
                            System.out.println("\n--- Recently Sent Messages ---");
                            System.out.println(Message.printMessages());
                            break;
                            
                        case "3":
                            System.out.println("Exiting QuickChat. Goodbye!");
                            running = false;
                            break;
                            
                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    }
                }
            }
        }
        
        scanner.close();
        }
    }
}
