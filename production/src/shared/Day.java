package shared;

//Import statements
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Project      : health_tracker
 * File         : Day.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that is associated with one date, stores historical information
 *                  (exercise/diet/weight/goal history) or future goals
 */

public class Day implements Comparable<Day>{
    //Local variables
    private final LocalDate date;
    private ArrayList<Exercise> exercises = new ArrayList<>(10);
    private ArrayList<Meal> meals = new ArrayList<>(10);
    private ArrayList<Weight> weights = new ArrayList<>(3);
    private ArrayList<Goal> goals = new ArrayList<>(3);

    //Constructors
    public Day(LocalDate date){
        this.date = date;
    }
    public Day(){
        this.date = LocalDate.now();
    }

    //Getters
    public LocalDate getDate(){
        return date;
    }
    public ArrayList<Exercise> getExercises(){
        return exercises;
    }
    public ArrayList<Meal> getMeals(){
        return meals;
    }
    public ArrayList<Weight> getWeights(){
        return weights;
    }
    public ArrayList<Goal> getGoals(){
        return goals;
    }

    //Adders
    public boolean addExercise(Exercise exercise){
        if(!inFuture()){
            exercises.add(exercise);
            return true;
        }
        return false;
    }
    public boolean addMeal(Meal meal){
        if(!inFuture()){
            meals.add(meal);
            return true;
        }
        return false;
    }
    public void addGoal(Goal goal){
        goals.add(goal);
    }
    public boolean addWeight(Weight weight){
        if(weights.size() < 3 && !inFuture()){
            weights.add(weight);
            return true;
        }
        return false;
    }

    //Removers
    public void removeExercise(Exercise exercise){
        exercises.remove(exercise);
    }
    public void removeMeal(Meal meal){
        meals.remove(meal);
    }
    public void removeGoal(Goal goal){
        goals.remove(goal);
    }
    public void removeWeight(Weight weight){
        weights.remove(weight);
    }

    //Checks if this Day is in the future (disallows meals, exercises, weights)
    public boolean inFuture(){
        return date.isAfter(LocalDate.now());
    }

    @Override
    public int compareTo(Day d) {
        return date.isBefore(d.getDate()) ? -1 : (date.equals(d.getDate()) ? 0 : 1);
    }
}
