package sample;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private final String DELIM = "#";
    private final File FILE = new File("database.txt");
    private enum fileToken{
        USERNAME(0),
        PASSWORD(1),
        SALT(2),
        EMAIL(3),
        NAME(4);

        public final int value;
        private fileToken(int pos){
            this.value = pos;
        }
    }


    //Constructor
    public ClientThread(Socket socket){
        this.socket = socket;
        System.out.println("Client created");
    }

    private String[] readFileLine(String match, fileToken pos){
        try {
            //Read from file
            BufferedReader reader = new BufferedReader(new FileReader(FILE));

            //Loop through string for a match
            String[] tokens;
            String line;
            while ((line = reader.readLine()) != null){
                tokens = line.split("#");
                if(tokens.length == 5){
                    if(tokens[pos.value].equalsIgnoreCase(match)){
                        reader.close();
                        return tokens;
                    }
                }
            }
            reader.close();

        }catch(IOException exception){
            System.out.println("Can't read from file");
            exception.printStackTrace();
        }
        return null;
    }

    private String getSalt(String username){
        String[] tokens = readFileLine(username, fileToken.USERNAME);
        if(tokens != null){
            return tokens[2];
        }else{
            return null;
        }
    }

    private Boolean verifyCredentials(String username, String hashPass){
        Boolean valid = false;
        String[] tokens = readFileLine(username, fileToken.USERNAME);
        if(tokens != null){
            if(hashPass.equals(tokens[1])){
                valid = true;
            }
        }
        return valid;
    }

    private Boolean registerUser(String username, String password, String salt, String email, String name){
        if(readFileLine(username, fileToken.USERNAME) == null && readFileLine(email, fileToken.EMAIL) == null){
            String toAppend = username + DELIM + password + DELIM + salt + DELIM + email + DELIM + name;
            return Server.writeToDatabase(toAppend, FILE);
        }else{
            return false;
        }
    }

    private void sendMessage(String message, PrintWriter outputStream){
        System.out.println("Sending: " + message);
        outputStream.println(message);
    }

    private void processMessage(String message, PrintWriter outputStream){
        System.out.println("Message received: " + message);
        String[] tokens = message.split("#");
        switch(tokens[0]){
            case "getSalt":
                sendMessage(getSalt(tokens[1]), outputStream);
                break;
            case "login":
                Boolean valid = verifyCredentials(tokens[1], tokens[2]);
                sendMessage(valid.toString(), outputStream);
                break;
            case "register":
                for(String token : tokens){
                    System.out.println(token);
                }
                Boolean success = registerUser(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                sendMessage(success.toString(), outputStream);
                break;
        }
    }

    public void run() {
        //Setting up input and output
        BufferedReader inStream = null;
        PrintWriter outStream = null;
        try {
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outStream = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException exception) {
            return;
        }
        System.out.println("Working");

        //Wait for messages from client
        String message;
        while (true){
            try{
                System.out.println("Reached waiting");
                //Read incoming message
                message = inStream.readLine();
                //Check if null/client wishes to disconnect
                if ((message == null) || message.equalsIgnoreCase("disconnect")) {
                    System.out.println("Closed connection");
                    socket.close();
                    return;
                }else{
                    //Handle message
                    processMessage(message, outStream);
                }
            }catch (IOException exception) {
                //exception.printStackTrace();
                System.out.println("Client disconnected: Closing thread");
                return;
            }
        }
    }
}
