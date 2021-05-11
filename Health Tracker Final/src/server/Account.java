package server;

//Import statements
import java.io.Serializable;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Project      : health_tracker
 * File         : Account.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Represents a unique account for the health tracker service
 *                  storing user details and providing member for data verification
 */

public class Account implements Serializable {
    private static final long serialVersionUID = 8219501L;
    //Account variables
    private String userName, fullName, email;
    private final byte[] salt;
    private byte[] hashPass;

    //Account constructor
    public Account(String userName, byte[] salt, byte[] hashPass, String fullName, String email){
        this.userName = userName.toLowerCase();
        this.salt = salt;
        this.hashPass = hashPass;
        this.fullName = fullName;
        this.email = email.toLowerCase();
    }

    //Getters
    public String getUserName(){
        return userName;
    }
    public byte[] getSalt(){
        return salt;
    }
    public String getFullName(){
        return fullName;
    }
    public String getEmail(){
        return email;
    }

    //Setters
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //Password handling (security sensitive)
    public boolean checkPassword(byte[] hashPass){
        return Arrays.equals(this.hashPass, hashPass);
    }
    public boolean changePassword(byte[] hashPass, byte[] newHashPass){
        if(checkPassword(hashPass)){
            this.hashPass = newHashPass;
            return true;
        }
        return false;
    }

    //Checkers
    public static boolean checkEmailFormat(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        return pattern.matcher(email).matches();
    }

    //Test harness
    public static void main(String[] args) {
        //--------- SETUP ---------
        byte[] saltBytes = "salt bytes to test".getBytes();
        byte[] hashPassBytes = "password bytes to test".getBytes();
        byte[] hashPassBytes2 = "password 2 bytes to test".getBytes();
        Account account = new Account("usser123", saltBytes, hashPassBytes, "First LastName", "email@email.com");
        //---------TEST A: PASSWORD FUNCTIONS---------
        boolean successA1 = account.checkPassword(hashPassBytes);
        boolean successA2 = !account.checkPassword(hashPassBytes2);

        boolean successA3 = account.changePassword(hashPassBytes, hashPassBytes2);
        boolean successA4 = !account.changePassword(hashPassBytes, hashPassBytes2);
        //---------TEST B: EMAIL FORMAT CHECKER---------
        boolean successB1 = Account.checkEmailFormat("example.email@gmail.co.uk");
        boolean successB2 = !Account.checkEmailFormat("exam£le$@gmail.co.uk");
        boolean successB3 = !Account.checkEmailFormat("example1.emailgmail.co.uk");


        //TEST RESULTS
        System.out.println("---------TEST A: RESULTS---------");
        System.out.println("CORRECT PASSWORD CHECK  : \t" + (successA1 ? "Pass" : "Fail"));
        System.out.println("INCORRECT PASSWORD CHECK: \t" + (successA2 ? "Pass" : "Fail"));
        System.out.println("CHANGE PASSWORD CORRECT : \t" + (successA3 ? "Pass" : "Fail"));
        System.out.println("CHANGE PASSWORD INCORRECT: \t" + (successA4 ? "Pass" : "Fail"));
        System.out.println("---------TEST B: RESULTS---------");
        System.out.println("VALID EMAIL ADDRESS     : \t" + (successB1 ? "Pass" : "Fail"));
        System.out.println("INVALID EMAIL (SYMBOLS) : \t" + (successB2 ? "Pass" : "Fail"));
        System.out.println("INVALID EMAIL (NO @)    : \t" + (successB3 ? "Pass" : "Fail"));
    }

}
