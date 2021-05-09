package client;

//Import statement
import shared.Day;
import shared.Drink;
import shared.Food;
import shared.Weight;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project      : health_tracker
 * File         : UserData.java
 * Last Edit    : 08/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : A class to hold local user data for our health tracker app
 */

public class UserData {
    //Constant Variables
    public static final double CM_TO_INCHES = 0.3937008;
    public static final String FOOD_TYPES_FILE_PATH = "./client_data/foodTypes.ser";
    public static final String DRINK_TYPES_FILE_PATH = "./client_data/drinkTypes.ser";
    //User related variables
    private int heightCm;
    private Weight currentWeight;
    private final ArrayList<Day> userDays;
    //Food and drink arraylists
    private final ArrayList<Food> foodList;
    private final ArrayList<Drink> drinkList;

    //Constructor
    public UserData(int heightCm, Weight currentWeight){
        this.heightCm = heightCm;
        this.currentWeight = currentWeight;
        this.userDays = new ArrayList<>();
        userDays.add(new Day());
        userDays.get(0).addWeight(currentWeight);
        //Load in food & drink types
        foodList = (ArrayList<Food>) readObject(FOOD_TYPES_FILE_PATH);
        drinkList = (ArrayList<Drink>) readObject(DRINK_TYPES_FILE_PATH);
    }

    //Getters
    public Day getDay(LocalDate date){
        int pos = Collections.binarySearch(userDays, new Day(date));
        return userDays.get(pos);
    }
    public int getHeightCm() {
        return heightCm;
    }
    public int getHeightInches(){
        return Math.toIntExact(Math.round(heightCm*CM_TO_INCHES));
    }
    public Weight getCurrentWeight(){
        return currentWeight;
    }
    public ArrayList<Drink> getDrinkList() {
        return drinkList;
    }
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    //Setters
    public void setHeightCm(int heightCm){
        this.heightCm = heightCm;
    }
    public void setHeightImperial(int feet, int inches){
        this.heightCm = Math.toIntExact(Math.round((feet*12 + inches)/CM_TO_INCHES));
    }
    public void setCurrentWeight(Weight weight){
        currentWeight = weight;
    }

    //Adders
    public boolean addDay(Day day){
        //Check for duplicate days
        for(Day d : userDays){
            if(d.getDate().equals(day.getDate())){
                return false;
            }
        }
        userDays.add(day);
        return true;
        //sortDays();
    }
    public boolean addDrink(Drink drink){
        for(Drink d : drinkList){
            if(d.getName().equalsIgnoreCase(drink.getName())){
                return false;
            }
        }
        drinkList.add(drink);
        return saveObject(drinkList, DRINK_TYPES_FILE_PATH);
    }
    public boolean addFood(Food food){
        for(Food f : foodList){
            if(f.getName().equalsIgnoreCase(food.getName())){
                return false;
            }
        }
        foodList.add(food);
        return saveObject(foodList, FOOD_TYPES_FILE_PATH);
    }
    //Removers
    public void removeFood(Food food){
        foodList.remove(food);
        saveObject(foodList, FOOD_TYPES_FILE_PATH);
    }
    public void removeDrink(Drink drink){
        drinkList.remove(drink);
        saveObject(drinkList, DRINK_TYPES_FILE_PATH);
    }

    //Sort day array
    public void sortDays(){
        Collections.sort(userDays);
    }

    //Object loader (used for food and drink types)
    private static Object readObject(String filePath){
        Object object = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded From: " + filePath);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("FAILED TO LOAD: " + filePath);
        }
        return object;
    }
    //Object Saver (used when updating food and drink types)
    private static boolean saveObject(Object object, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.println("Saved To: " + filePath);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("FAILED TO SAVE: " + filePath);
            return false;
        }
    }

    //Test harness
    /*public static void main(String[] args) {
        //Creating new UserData object
        UserData userData = new UserData(200, new Weight(10, 1));
        //Outputting all food
        ArrayList<Food> foodList = userData.getFoodList();
        for (Food food : foodList) {
            System.out.print(food.getName() + ", ");
        }
        System.out.println("\b\b");

        //Adding a new food object
        Food spaghetti = new Food("Spaghetti", 201);
        userData.addFood(spaghetti);

        //Outputting all food
        for (Food food : foodList) {
            System.out.print(food.getName() + ", ");
        }
        System.out.println("\b\b");

        //Removing the food object and outputting all food again
        userData.removeFood(spaghetti);
        for (Food food : foodList) {
            System.out.print(food.getName() + ", ");
        }
        System.out.println("\b\b");
    }*/
}
