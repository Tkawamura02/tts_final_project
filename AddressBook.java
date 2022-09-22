package tts_final_project;

public class AddressBook {
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    
    public AddressBook(String first, String last, String phone, String email) {
        this.firstName = first;
        this.lastName = last;
        this.phoneNumber = phone;
        this.emailAddress = email;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getEmail() {
        return this.emailAddress;
    }
    
    //i do not think setters are required for the tasks of this project
    
    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + " \nPhone Number: " + this.phoneNumber + " \nEmail: " + this.emailAddress + "\n\n";
    }
    
}