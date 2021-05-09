package shared;

import server.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project      : health_tracker
 * File         : Food.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that represents one serving of a Food item,
 *                  its name & calories
 */

public class Food implements Serializable {
    @Serial
    private static final long serialVersionUID = 9147349L;

    //Class variables
    private final String name;
    private final int caloriesPerPortion;

    //Constructor
    public Food(String name, int caloriesPerPortion){
        this.name = name;
        this.caloriesPerPortion = caloriesPerPortion;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getCaloriesPerPortion() {
        return caloriesPerPortion;
    }

    public static void main(String[] args) {
        ArrayList<Food> drinkList;

        try {
            FileInputStream fileIn = new FileInputStream("./client_data/foodTypes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            drinkList = (ArrayList<Food>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded Accounts");
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("Cant load Accounts");
            return;
        }

        for(Food drink : drinkList){
            System.out.println(drink.getName() + ", " + drink.getCaloriesPerPortion());
        }



        /*ArrayList<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink("Apple Juice", 114));
        drinkList.add(new Drink("Coffee", 2));
        drinkList.add(new Drink("Milk", 125));
        drinkList.add(new Drink("Orange Juice", 112));
        drinkList.add(new Drink("Tea", 2));

        try {
            FileOutputStream fileOut = new FileOutputStream("./client_data/drinkTypes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(drinkList);
            out.close();
            fileOut.close();
            System.out.println("Updated User Database");
        } catch (IOException i) {
            i.printStackTrace();
        }*/

    }
}
