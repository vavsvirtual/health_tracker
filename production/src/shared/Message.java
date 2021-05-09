package shared;

import java.io.Serial;
import java.io.Serializable;

/**
 * Project      : health_tracker
 * File         : Message.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Carries information from server to client and the other way,
 *                  including hashed passwords, salts, names, emails, etc.
 */

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 605119L;

    public enum messageType{
        SALT_REQUEST,
        LOGIN,
        REGISTER
    }

    private Boolean success;
    private final String[] stringMessage;
    private final byte[][] byteMessage;
    private messageType type;

    public Message(messageType type, String[] stringMessage, byte[][] byteMessage){
        this.type = type;
        this.stringMessage = stringMessage;
        this.byteMessage = byteMessage;
    }

    public Message(Boolean success, String[] stringMessage, byte[][] byteMessage){
        this.success = success;
        this.stringMessage = stringMessage;
        this.byteMessage = byteMessage;
    }

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
}
