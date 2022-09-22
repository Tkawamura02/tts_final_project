package FinalProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author coleb
 */
public class MainDriver {
    
    public static void main(String args[]) {
        
        Scanner input = new Scanner(System.in);
        List<AddressBook> addressBook = new ArrayList<>();
        boolean continueLoop = true;
        int userOption;
        int userOption2;
        String first;
        String last;
        String phone;
        String email;
        boolean unique;
        String temp;
        boolean removed;
        
        while (continueLoop) {
           
            System.out.println("1) Add an Entry\n"
                             + "2) Remove an Entry\n"
                             + "3) Search for a specific Entry\n"
                             + "4) Print Address Book\n"
                             + "5) Delete Book\n"
                             + "6) Quit\n\n"
                             + "Please choose what you'd like to do with the database:");
            
            try{
                userOption = input.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("\nPlease enter a valid input type.");
                userOption = 100;
                
                    input.nextLine();
                
            }
  
            switch(userOption) {
                case 1:
                    //add an entry
                    System.out.println("Enter their first name: ");
                    first = input.next();
                    System.out.println("Enter their last name: ");
                    last = input.next();
                    System.out.println("Enter their phone number: ");
                    phone = input.next();
                    System.out.println("Enter their email address: ");
                    email = input.next();
                    
                    if(!email.contains("@")) {
                        while (!email.contains("@")) {
                            System.out.println("Please enter a valid email address: ");
                            email = input.next();
                            input.nextLine();
                        }
                    }
                    
                    unique = checkEmail(email, addressBook);
                    
                        while (!unique) {
                            System.out.println("Enter a unique email address: ");
                            email = input.nextLine();
                            unique = checkEmail(email, addressBook);
                        }
                    
                    
                    addressBook.add(new AddressBook(first,last,phone,email));
                    System.out.println("\nNew entry added successfully\n");
                    
                    break;
                case 2:
                    //remove an entry
                    email = "";
                    removed = false;
                    if(addressBook.isEmpty()) {
                        System.out.println("\nAddress book is already empty. Nothing to remove.\n");
                        break;
                    }
      
                    System.out.println("Enter an entry's email you want to remove:");
                    email = input.next();
                    input.nextLine();
                    
                    System.out.println("You selected Email: " + email);
                    
                    for (int i = 0; i < addressBook.size(); i++) {
                        if(addressBook.get(i).getEmail().equalsIgnoreCase(email)) {
                            System.out.println("Removing the following entry: ");
                            System.out.println(addressBook.get(i).toString());
                            addressBook.remove(i);
                            removed = true;
                            break;
                        }
                    }
                    if(!removed){
                        System.out.println("\nEntry not found. No entry removed.\n"); 
                    }
                    
                    break;
                case 3:
                    //search for a specific entry
                    
                    if (addressBook.isEmpty()) {
                        System.out.println("Address book is empty. Returning to main menu");
                        break;
                    }
                    
                    System.out.println("\nSelect of of the following options to search by:\n");
                    System.out.println("1) First Name\n"
                                     + "2) Last Name\n"
                                     + "3) PhoneNumber\n"
                                     + "4) Email\n");
                    
                    try{
                        userOption2 = input.nextInt();
                    } catch(InputMismatchException e) {
                        System.out.println("\nPlease enter a valid input type.");
                        userOption2 = 100;
                
                        input.nextLine();
                    }
                    
                    switch(userOption2) {
                        case 1:
                            //first
                            System.out.println("Enter your search: ");
                            temp = input.next();
                            temp = temp.toLowerCase();
                            
                            for (int i =0; i<addressBook.size(); i++) {
                                if(addressBook.get(i).getFirstName().toLowerCase().contains(temp)){
                                    System.out.println("*************************\n"
                                            + addressBook.get(i).toString() +
                                            "\n*************************\n");
                                }
                            }
                            
                            break;
                        case 2:
                            //last
                            System.out.println("Enter your search: ");
                            temp = input.next();
                            temp = temp.toLowerCase();
                            
                            for (int i =0; i<addressBook.size(); i++) {
                                if(addressBook.get(i).getLastName().toLowerCase().contains(temp)){
                                    System.out.println("*************************\n"
                                            + addressBook.get(i).toString() +
                                            "\n*************************\n");
                                }
                            }
                            
                            break;
                        case 3:
                            //phone
                            System.out.println("Enter your search: ");
                            temp = input.next();
                            
                            for (int i =0; i<addressBook.size(); i++) {
                                if(addressBook.get(i).getPhoneNumber().contains(temp)){
                                    System.out.println("*************************\n"
                                            + addressBook.get(i).toString() +
                                            "\n*************************\n");
                                }
                            }
                            break;
                        case 4:
                            //email
                            System.out.println("Enter your search: ");
                            temp = input.next();
                            temp = temp.toLowerCase(); //TODO: check that @ key can be handled
                            
                            for (int i =0; i<addressBook.size(); i++) {
                                if(addressBook.get(i).getEmail().toLowerCase().contains(temp)){
                                    System.out.println("*************************\n"
                                            + addressBook.get(i).toString() +
                                            "*************************\n");
                                }
                            }
                            break;
                        default:
                            System.out.println("Enter valid input 1-4.\nReturning to main menu.");
                    }

                    
                    break;
                case 4:
                    //print address book
                    if(addressBook.isEmpty()) {
                        System.out.println("\nAddress book is already empty\n");
                        break;
                    }
                    for (int i = 0; i < addressBook.size(); i++) {
                        System.out.print(addressBook.get(i).toString());
                    }
                    break;
                case 5: 
                    //delete address book
                    addressBook.clear();
                    if(addressBook.isEmpty()) {
                        System.out.println("Successfully deleted the address book");
                    }
                    else {
                        System.out.println("Something went wrong trying to delete this");
                    }
                    break;
                case 6:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Please enter an integer range 1-6.\n");
            }
                  
        }
        
    }
    
    public static boolean checkEmail(String email, List<AddressBook> list) {
        if (list.isEmpty()) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
    
}
