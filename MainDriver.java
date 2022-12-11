
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainDriver {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<AddressBook> addressBook = new ArrayList<>();
        int opt1;
        int opt2;
        boolean diff;
        boolean removed;
        boolean continueLoop = true;
        String first;
        String last;
        String phone;
        String email;
        String temp;
        
        while (continueLoop) {
            System.out.println("1) Add an Entry\n"
                             + "2) Remove an Entry\n"
                             + "3) Search for a specific Entry\n"
                             + "4) Print Address Book\n"
                             + "5) Delete Book\n"
                             + "6) Quit\n\n"
                             + "Please choose what you'd like to do with the database:");
            try{
                opt1 = in.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("\nPlease enter a valid input type.");
                opt1 = 100;
                    in.nextLine();
            }
            switch(opt1) {
                case 1:
                    System.out.println("First Name: ");
                    first = in.next();
                    System.out.println("Last Name: ");
                    last = in.next();
                    System.out.println("Phone Number: ");
                    phone = in.next();
                    System.out.println("Email Address: ");
                    email = in.next();
                    if(!email.contains("@")) {
                        while (!email.contains("@")) {
                            System.out.println("Insert email again please: ");
                            email = in.next();
                            in.nextLine();
                        }
                    }
                    diff = checkEmail(email, addressBook);
                        while (!diff) {
                            System.out.println("Please enter a unique email address: ");
                            email = in.nextLine();
                            diff = checkEmail(email, addressBook);
                        }
                    addressBook.add(new AddressBook(first,last,phone,email));
                    System.out.println("\nSuccessfully Added!\n");
                    break;
                    
                case 2:
                    email = "";
                    removed = false;
                    if(addressBook.isEmpty()) {
                        System.out.println("\nAddress book is already empty. Nothing to remove.\n");
                        break;
                    }
                    System.out.println("Please enter email you want to remove: ");
                    email = in.next();
                    in.nextLine();
                    System.out.println("You selected Email: " + email);
                    for (int i = 0; i < addressBook.size(); i++) {
                        if(addressBook.get(i).getEmail().equalsIgnoreCase(email)) {
                            System.out.println("Removing the following entry: " + addressBook.get(i).toString());
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
                    if (addressBook.isEmpty()) {
                        System.out.println("Address book is already empty. Returning back to main menu.");
                        break;
                    }
                    System.out.println("\nSelect of of the following options to search by:\n");
                    System.out.println("1) First Name\n"
                                     + "2) Last Name\n"
                                     + "3) PhoneNumber\n"
                                     + "4) Email\n");
                    try{
                    	opt2 = in.nextInt();
                    } catch(InputMismatchException e) {
                        System.out.println("\nPlease enter a valid input type.");
                        opt2 = 100;
                        in.nextLine();
                    }
                    switch(opt2) {
                        case 1:
                            System.out.println("Search: ");
                            temp = in.next();
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
                            System.out.println("Search: ");
                            temp = in.next();
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
                            System.out.println("Search: ");
                            temp = in.next();
                            for (int i =0; i<addressBook.size(); i++) {
                                if(addressBook.get(i).getPhoneNumber().contains(temp)){
                                    System.out.println("*************************\n"
                                            + addressBook.get(i).toString() +
                                            "\n*************************\n");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Enter your search: ");
                            temp = in.next();
                            temp = temp.toLowerCase();
                            for (int i =0; i<addressBook.size(); i++) {
                                if(addressBook.get(i).getEmail().toLowerCase().contains(temp)){
                                    System.out.println("*************************\n"
                                            + addressBook.get(i).toString() +
                                            "*************************\n");
                                }
                            }
                            break;
                        default:
                            System.out.println("Please enter a valid input between 1-4.\nReturning back to main menu.");
                    }

                    break;
                case 4:
                    if(addressBook.isEmpty()) {
                        System.out.println("\nAddress book is already empty.\n");
                        break;
                    }
                    for (int i = 0; i < addressBook.size(); i++) {
                        System.out.print(addressBook.get(i).toString());
                    }
                    break;
                case 5: 
                    addressBook.clear();
                    if(addressBook.isEmpty()) {
                        System.out.println("Successfully deleted the address book!");
                    }
                    else {
                        System.out.println("Something went wrong trying to delete this. Please try again later.");
                    }
                    break;
                case 6:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Please enter a valid input between 1-6.\n");
            }
        }
        in.close();
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