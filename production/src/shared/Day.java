package shared;

//Import statements
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Project      : health_tracker
 * File         : Day.java
 * Last Edit    : 09/05/2021
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

    //Test harness
    public static void main(String[] args) {
        //--------- SETUP ---------
        Day pastDay = new Day(LocalDate.now().minusDays(200));
        Day currentDay = new Day(LocalDate.now());
        Day futureDay = new Day(LocalDate.now().plusDays(200));
        //---------TEST A: OTHER FUNCTIONS---------
        //inFuture test
        boolean successA1 = (!pastDay.inFuture() && !currentDay.inFuture() && futureDay.inFuture());
        //Compare test
        boolean successA2, successA3, successA4;
        //Less than date
        successA2 = currentDay.compareTo(futureDay) < 0;
        //Greater than date
        successA3 = currentDay.compareTo(pastDay) > 0;
        //Equal date
        successA4 = currentDay.compareTo(new Day(LocalDate.now())) == 0;

        //---------TEST B: ADDING TO DAY LIMITS---------
        //Weight
        boolean successB1 = currentDay.addWeight(new Weight(100f));
        boolean successB2 = !futureDay.addWeight(new Weight(100f));
        currentDay.addWeight(new Weight(100f));
        currentDay.addWeight(new Weight(100f));
        boolean successB3 = !currentDay.addWeight(new Weight(100f));

        //Meals
        boolean successB4 = currentDay.addMeal(new Meal());
        boolean successB5 = !futureDay.addMeal(new Meal());

        //Exercise
        boolean successB6 = currentDay.addExercise(new Exercise(Exercise.ExerciseType.CYCLING, 20));
        boolean successB7 = !futureDay.addExercise(new Exercise(Exercise.ExerciseType.CYCLING, 20));

        //TEST RESULTS
        System.out.println("---------TEST A: RESULTS---------");
        System.out.println("IN FUTURE FUNC  : \t" + (successA1 ? "Pass" : "Fail"));
        System.out.println("COMPARE TO <    : \t" + (successA2 ? "Pass" : "Fail"));
        System.out.println("COMPARE TO >    : \t" + (successA3 ? "Pass" : "Fail"));
        System.out.println("COMPARE TO =    : \t" + (successA4 ? "Pass" : "Fail"));
        System.out.println("---------TEST B: RESULTS---------");
        System.out.println("ADDING WEIGHT (NOW)     : \t" + (successB1 ? "Pass" : "Fail"));
        System.out.println("ADDING WEIGHT (FUTURE)  : \t" + (successB2 ? "Pass" : "Fail"));
        System.out.println("ADDING WEIGHT (NUM>3)   : \t" + (successB3 ? "Pass" : "Fail"));
        System.out.println("ADDING MEAL (NOW)       : \t" + (successB4 ? "Pass" : "Fail"));
        System.out.println("ADDING MEAL (FUTURE)    : \t" + (successB5 ? "Pass" : "Fail"));
        System.out.println("ADDING EXERCISE (NOW)   : \t" + (successB6 ? "Pass" : "Fail"));
        System.out.println("ADDING EXERCISE (FUTURE): \t" + (successB7 ? "Pass" : "Fail"));
    }
}
