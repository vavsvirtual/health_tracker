package shared;

//Import statements
import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Project      : health_tracker
 * File         : Meal.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores an array of Food/Drink objects
 *                  that make up a whole meal eg. Pasta + Meatballs + Coffee
 */

public class Meal {
    //Creating variables to store food, drink and meal type
    private ArrayList<Pair<Integer, Food>> foodList;
    private ArrayList<Pair<Integer, Drink>> drinkList;
    private MealType mealType;

    //Enum to enforce meal types
    public enum MealType{
        BREAKFAST,
        LUNCH,
        DINNER,
        DESERT,
        SNACK
    }

    //Constructors
    public Meal(ArrayList<Pair<Integer, Food>> foodList, ArrayList<Pair<Integer, Drink>> drinkList){
        this.foodList = foodList;
        this.drinkList = drinkList;
    }
    public Meal(ArrayList<Pair<Integer, Food>> foodList, ArrayList<Pair<Integer, Drink>> drinkList, MealType mealType){
        this(foodList, drinkList);
        this.mealType = mealType;
    }
    public Meal(){
        this.foodList = new ArrayList<>();
        this.drinkList = new ArrayList<>();
    }
    public Meal(MealType mealType){
        this();
        this.mealType = mealType;
    }

    //Getters
    public ArrayList<Pair<Integer, Drink>> getDrinkList() {
        return drinkList;
    }
    public MealType getMealType() {
        return mealType;
    }
    public ArrayList<Pair<Integer, Food>> getFoodList() {
        return foodList;
    }

    //Setters
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    //Adders
    public void addFood(int amount, Food food){
        //Check if food is already in list
        for(Pair<Integer, Food> p : foodList){
            //If it is simply increment the amount, remove it then go to adding
            if(p.getValue().equals(food)){
                amount += p.getKey();
                foodList.remove(p);
                break;
            }
        }
        //Add the new food
        if(amount > 0){
            foodList.add(new Pair<>(amount, food));
        }
    }
    public void addDrink(int amount, Drink drink){
        //Check if drink is already in list
        for(Pair<Integer, Drink> p : drinkList){
            //If it is simply increment the amount, remove it then go to adding
            if(p.getValue().equals(drink)){
                amount += p.getKey();
                drinkList.remove(p);
                break;
            }
        }
        //Add the new drink
        if(amount > 0){
            drinkList.add(new Pair<>(amount, drink));
        }
    }
    //Removers
    public void removeDrink(Drink drink){
        //Check for drink
        for(Pair<Integer, Drink> p : drinkList){
            //If exists remove it
            if(p.getValue().equals(drink)){
                drinkList.remove(p);
                break;
            }
        }
    }
    public void removeFood(Food food){
        //Check for food
        for(Pair<Integer, Food> p : foodList){
            //If exists remove it
            if(p.getValue().equals(food)){
                foodList.remove(p);
                break;
            }
        }
    }


    //Test harness
    public static void main(String[] args) {
        //--------- SETUP ---------
        Meal meal = new Meal();
        Food testFood1 = new Food("testFood1", 20);
        Food testFood2 = new Food("testFood2", 20);
        Drink testDrink1 = new Drink("testDrink1", 20);
        Drink testDrink2 = new Drink("testDrink2", 20);
        //---------TEST A: ADDING/INCREMENTING/REMOVING FOOD---------
        //Testing adding food
        boolean successA1 = false;
        meal.addFood(2, testFood1);
        for(Pair<Integer, Food> p: meal.getFoodList()){
            if(p.getValue().equals(testFood1)){
                successA1 = true;
            }
        }
        //Testing adding food with amount <= 0
        boolean successA2 = true;
        meal.addFood(0, testFood2);
        for(Pair<Integer, Food> p: meal.getFoodList()){
            if(p.getValue().equals(testFood2)){
                successA2 = false;
            }
        }
        //Incrementing food
        boolean successA3 = false;
        meal.addFood(2, testFood1);
        for(Pair<Integer, Food> p: meal.getFoodList()){
            if(p.getValue().equals(testFood1) && p.getKey() == 4){
                successA3 = true;
            }
        }
        //Removing food (negative increment)
        boolean successA4 = true;
        meal.addFood(-4, testFood1);
        for(Pair<Integer, Food> p: meal.getFoodList()){
            if(p.getValue().equals(testFood1)){
                successA4 = false;
            }
        }
        //Removing food (meal.removeFood())
        boolean successA5 = true;
        meal.addFood(4, testFood1);
        meal.removeFood(testFood1);
        for(Pair<Integer, Food> p: meal.getFoodList()){
            if(p.getValue().equals(testFood1)){
                successA5 = false;
            }
        }

        //---------TEST B: ADDING/INCREMENTING/REMOVING DRINKS---------
        //Testing adding Drink
        boolean successB1 = false;
        meal.addDrink(2, testDrink1);
        for(Pair<Integer, Drink> p: meal.getDrinkList()){
            if(p.getValue().equals(testDrink1)){
                successB1 = true;
            }
        }
        //Testing adding Drink with amount <= 0
        boolean successB2 = true;
        meal.addDrink(0, testDrink2);
        for(Pair<Integer, Drink> p: meal.getDrinkList()){
            if(p.getValue().equals(testDrink2)){
                successB2 = false;
            }
        }
        //Incrementing Drink
        boolean successB3 = false;
        meal.addDrink(2, testDrink1);
        for(Pair<Integer, Drink> p: meal.getDrinkList()){
            if(p.getValue().equals(testDrink1) && p.getKey() == 4){
                successB3 = true;
            }
        }
        //Removing Drink (negative increment)
        boolean successB4 = true;
        meal.addDrink(-4, testDrink1);
        for(Pair<Integer, Drink> p: meal.getDrinkList()){
            if(p.getValue().equals(testDrink1)){
                successB4 = false;
            }
        }
        //Removing Drink (meal.removeDrink())
        boolean successB5 = true;
        meal.addDrink(4, testDrink1);
        meal.removeDrink(testDrink1);
        for(Pair<Integer, Drink> p: meal.getDrinkList()){
            if(p.getValue().equals(testDrink1)){
                successB5 = false;
            }
        }

        //TEST RESULTS
        System.out.println("---------TEST A: RESULTS---------");
        System.out.println("ADD FOOD AMOUNT > 0: \t" + (successA1 ? "Pass" : "Fail"));
        System.out.println("ADD FOOD AMOUNT < 0: \t" + (successA2 ? "Pass" : "Fail"));
        System.out.println("INCREMENT FOOD NO.: \t" + (successA3 ? "Pass" : "Fail"));
        System.out.println("REMOVE FOOD VIA -INC: \t" + (successA4 ? "Pass" : "Fail"));
        System.out.println("REMOVE FOOD VIA FUNC: \t" + (successA5 ? "Pass" : "Fail"));

        System.out.println("---------TEST B: RESULTS---------");
        System.out.println("ADD DRINK AMOUNT > 0: \t" + (successB1 ? "Pass" : "Fail"));
        System.out.println("ADD DRINK AMOUNT < 0: \t" + (successB2 ? "Pass" : "Fail"));
        System.out.println("INCREMENT DRINK NO.: \t" + (successB3 ? "Pass" : "Fail"));
        System.out.println("REMOVE DRINK VIA -INC: \t" + (successB4 ? "Pass" : "Fail"));
        System.out.println("REMOVE DRINK VIA FUNC: \t" + (successB5 ? "Pass" : "Fail"));

    }

}
