package server;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Project      : health_tracker
 * File         : Account.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Represents a unique account for the health tracker service
 *                  storing user details and providing member for data verification
 */

public class Account{
    private String userName, fullName, email;
    private final byte[] salt;
    private byte[] hashPass;

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
        if(Arrays.equals(this.hashPass, hashPass)){
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

}
