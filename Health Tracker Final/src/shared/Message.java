package shared;

//Import statement
import java.io.Serializable;

/**
 * Project      : health_tracker
 * File         : Message.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Carries information from server to client and the other way,
 *                  including hashed passwords, salts, names, emails, etc.
 */

public class Message implements Serializable {
    private static final long serialVersionUID = 605119L;
    //Message type enum to differentiate between requests
    public enum messageType{
        SALT_REQUEST,
        LOGIN,
        REGISTER,
        CREATE_GROUP,
        JOIN_GROUP,
        INVITE_TO_GROUP
    }

    //Message variables (stores info to be passed)
    private Boolean success;
    private final String[] stringMessage;
    private final byte[][] byteMessage;
    private messageType type;

    //Constructor (client -> server)
    public Message(messageType type, String[] stringMessage, byte[][] byteMessage){
        this.type = type;
        this.stringMessage = stringMessage;
        this.byteMessage = byteMessage;
    }

    //Constructor (client <- server)
    public Message(Boolean success, String[] stringMessage, byte[][] byteMessage){
        this.success = success;
        this.stringMessage = stringMessage;
        this.byteMessage = byteMessage;
    }

    //Getters
    public Boolean getSuccess() {
        return success;
    }
    public String[] getStringMessage() {
        return stringMessage;
    }
    public messageType getType() {
        return type;
    }
    public byte[][] getByteMessage() {
        return byteMessage;
    }

    //Test Harness not required
}
