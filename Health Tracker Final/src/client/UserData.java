package client;

//Import statement
import shared.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

    /**
     * Project      : health_tracker
     * File         : UserData.java
     * Last Edit    : 09/05/2021
     * PRG Lang     : Java
     * Author(s)    : Team 4.5 | Vav Scott 100287100
     *
     * Description  : A class to hold local user data for our health tracker app
     */

    public class UserData implements Serializable{
        private static final long serialVersionUID = 1039289L;
        //Constant Variables
        public static final double CM_TO_INCHES = 0.3937008;
        public static final String FOOD_TYPES_FILE_PATH = "./client_data/foodTypes.ser";
        public static final String DRINK_TYPES_FILE_PATH = "./client_data/drinkTypes.ser";
        public static final String FILE_PATH_BEGINNING = "./client_data/";
        //Account related variables
        private final String userName;
        private final String fullName;
        private final String email;
        private int heightCm;
        private Weight currentWeight;
        private final ArrayList<Day> userDays;
        //Food and drink lists
        private final ArrayList<Food> foodList;
        private final ArrayList<Drink> drinkList;

        //Constructor
        public UserData(String userName, String fullName, String email){
            this.userName = userName;
            this.fullName = fullName;
            this.email = email;
            //Create days arrayList
            this.userDays = new ArrayList<>();
            //Load in food & drink types
            foodList = (ArrayList<Food>) readObject(FOOD_TYPES_FILE_PATH);
            drinkList = (ArrayList<Drink>) readObject(DRINK_TYPES_FILE_PATH);
        }

        //Getters
        public Day getDay(LocalDate date){
            int pos = Collections.binarySearch(userDays, new Day(date));
            if(pos < 0){
                return null;
            }
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
        //Getters for user info
        public String getUserName() {
            return userName;
        }
        public String getEmail() {
            return email;
        }
        public String getFullName() {
            return fullName;
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
            sortDays();
            return true;
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
        public boolean addExercise(Exercise exercise, LocalDate localDate){
            Day day = getDay(localDate);
            if(day != null){
                return day.addExercise(exercise);
            }else{
                day = new Day(localDate);
                boolean added = day.addExercise(exercise);
                if(added){
                    added = addDay(day);
                }
                return added;
            }
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
        public static Object readObject(String filePath){
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
        public static boolean saveObject(Object object, String filePath) {
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

        /*//Test harness
        public static void main(String[] args) {
            //--------- SETUP ---------
            UserData userData = new UserData(200, new Weight(10, 1));
            //---------TEST A: FOOD LIST---------
            //Adding a new food object
            Food spaghetti = new Food("Spaghetti", 201);
            userData.addFood(spaghetti);
            //Checking for spaghetti food
            ArrayList<Food> foodList = userData.getFoodList();
            boolean successA1 = false;
            for (Food food : foodList) {
                if(food == spaghetti){
                    successA1 = true;
                    break;
                }
            }
            //Attempting to add a food object that already exists
            boolean successA2 = !userData.addFood(spaghetti);

            //Removing the food object
            userData.removeFood(spaghetti);
            boolean successA3 = true;
            for (Food food : foodList) {
                if(food == spaghetti){
                    successA3 = false;
                    break;
                }
            }
            //---------TEST B: DRINK LIST---------
            //Adding a new drink object
            Drink testDrink = new Drink("Test Drink", 20);
            userData.addDrink(testDrink);
            //Checking for testDrink
            ArrayList<Drink> drinkList = userData.getDrinkList();
            boolean successB1 = false;
            for (Drink drink : drinkList) {
                if(drink == testDrink){
                    successB1 = true;
                    break;
                }
            }
            //Attempting to add a drink object that already exists
            boolean successB2 = !userData.addDrink(testDrink);

            //Removing the drink object
            userData.removeDrink(testDrink);
            boolean successB3 = true;
            for (Drink drink : drinkList) {
                if(drink == testDrink){
                    successB3 = false;
                    break;
                }
            }

            //---------TEST C: DAY TESTS---------
            //Adding a day to user data
            userData.addDay(new Day(LocalDate.of(2007, 7,7)));
            //Attempting to get it back
            Day day = userData.getDay(LocalDate.of(2007, 7, 7));
            boolean successC1 = false;
            if(day != null){
                successC1 = true;
            }

            //Attempting to add a day with the same date as a preexisting day
            userData.addDay(new Day(LocalDate.of(2007, 7,7)));
            boolean successC2 = (userData.userDays.size() == 2);

            //Attempting to get non existant day
            day = userData.getDay(LocalDate.of(2008, 7, 7));
            boolean successC3 = true;
            if(day != null){
                successC3 = false;
            }
            //TEST RESULTS
            System.out.println("---------TEST A: RESULTS---------");
            System.out.println("ADDING UNIQUE FOOD: \t" + (successA1 ? "Pass" : "Fail"));
            System.out.println("ADD DUPLICATE FOOD: \t" + (successA2 ? "Pass" : "Fail"));
            System.out.println("REMOVE EXISTING FOOD: \t" + (successA3 ? "Pass" : "Fail"));
            System.out.println("---------TEST B: RESULTS---------");
            System.out.println("ADDING UNIQUE DRINK: \t" + (successB1 ? "Pass" : "Fail"));
            System.out.println("ADD DUPLICATE DRINK: \t" + (successB2 ? "Pass" : "Fail"));
            System.out.println("REMOVE EXISTING DRINK: \t" + (successB3 ? "Pass" : "Fail"));
            System.out.println("---------TEST C: RESULTS---------");
            System.out.println("ADD & GET UNIQUE DAY: \t" + (successC1 ? "Pass" : "Fail"));
            System.out.println("ADD A DUPLICATE DAY: \t" + (successC2 ? "Pass" : "Fail"));
            System.out.println("GET NON EXISTENT DAY: \t" + (successC3 ? "Pass" : "Fail"));

        }*/
    }