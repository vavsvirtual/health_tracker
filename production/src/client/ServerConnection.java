package client;

//Import statements
import javafx.util.Pair;
import shared.Message;
//Hashing imports
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
//Networking imports
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Project      : health_tracker
 * File         : ServerConnection.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Handles connection & communication to the server
 */

public class ServerConnection{
    //Input/Output stream variables
    private final ObjectOutputStream outStream;
    private final ObjectInputStream inStream;

    //Constructors
    public ServerConnection(int port, String host, int maxWaitMs) throws IOException {
        //Open connection, Set timeout for server response, create in/out streams
        Socket serverSocket = new Socket(host, port);
        serverSocket.setSoTimeout(maxWaitMs);
        outStream = new ObjectOutputStream(serverSocket.getOutputStream());
        inStream = new ObjectInputStream(serverSocket.getInputStream());
    }
    //Default constructor (localhost:3000) with 2 seconds before timeout
    public ServerConnection() throws IOException{
        this(3000, "localhost", 2000);
    }

    //Sending and receiving function
    private Message sendMessage(Message.messageType type, String[] stringMessage, byte[][] byteMessage){
        try{
            //Sending message
            outStream.writeObject(new Message(type,stringMessage,byteMessage));
            //Returning response
            return (Message)inStream.readObject();
        }catch(IOException | ClassNotFoundException exception){
            //Can't write object to outStream/Timeout in waiting for message
            exception.printStackTrace();
            return new Message(false, new String[] {"Couldn't contact server, check your internet"}, null);
        }
    }

    public Pair<Boolean, String> login(String username, String password){
        //Obtaining salt
        byte[] salt;
        Message res = sendMessage(Message.messageType.SALT_REQUEST, new String[]{username}, null);
        //If no salt obtained -> no account under that username
        if (!res.getSuccess()){
            System.out.println("LOGIN FAIL: Username not in Database");
            return new Pair(false, "No account under that username");
        }else{
            salt = res.getByteMessage()[0];
        }
        //Logging in
        try{
            //Hashing password & sending credentials
            byte[] hashPass = hashPassword(salt, password);
            res = sendMessage(Message.messageType.LOGIN, new String[]{username}, new byte[][]{hashPass});
            return new Pair(res.getSuccess(), res.getStringMessage()[0]);
        }catch(NoSuchAlgorithmException | InvalidKeySpecException exception){
            exception.printStackTrace();
            return new Pair(false, "Couldn't hash password, please try again");
        }
    }

    //Register
    public Pair<Boolean, String> register(String username, String password, String fullName, String email){
        //Checking if password meets minimum requirements
        if (!checkPassFormat(password)){
            return new Pair(false, "Password Requires 1 letter, 1 number and must be 8 characters or more");
        }
        //Hashing password
        byte[] salt = generateSalt();
        byte[] hashPass;
        try{
            hashPass = hashPassword(salt, password);
        }catch(NoSuchAlgorithmException | InvalidKeySpecException exception){
            exception.printStackTrace();
            return new Pair(false, "Couldn't hash password, please try again");
        }
        //Sending data to server and awaiting response
        String[] stringMessage = {username, fullName, email};
        Message res = sendMessage(Message.messageType.REGISTER, stringMessage, new byte[][]{salt, hashPass});
        return new Pair(res.getSuccess(), res.getStringMessage()[0]);
    }

    //Password Rule Validator
    public static boolean checkPassFormat(String password){
        //Setting up boolean values
        boolean containsLetter = false;
        boolean containsNumber = false;
        if(password.length() >= 8){
            for(char c : password.toCharArray()){
                if (Character.isLetter(c)){
                    containsLetter = true;
                }else if (Character.isDigit(c)){
                    containsNumber = true;
                }
            }
        }
        return (containsLetter && containsNumber);
    }

    //Password Hashing
    private byte[] hashPassword(byte[] salt, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
    //Generate salt
    private byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    //Test harness not required
}
