package shared;

/**
 * Project      : health_tracker
 * File         : Exercise.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores type of exercise, duration &/or distance
 */


public class Exercise{
    public enum ExerciseType{
        //Individual sports
        RUNNING,
        CYCLING,
        SWIMMING,
        WALKING,

        //Group sports
        FOOTBALL,
        RUGBY,
        TENNIS,
        BADMINTON
    }
    private static final double KM_TO_MILES = 0.6213712;
    private final ExerciseType type;
    private int durationMins;
    private double distanceKm;
    
    //Constructors
    public Exercise(ExerciseType type, int durationMins){
        this.type = type;
        this.durationMins = durationMins;
    }
    public Exercise(ExerciseType type, double distanceKm){
        this.type = type;
        this.distanceKm = distanceKm;

    }
    public Exercise(ExerciseType type, int durationMins, double distanceKm){
        this.type = type;
        this.durationMins = durationMins;
        this.distanceKm = distanceKm;
    }

    //Getters
    public ExerciseType getType() {
        return type;
    }
    public int getDurationMins() {
        return durationMins;
    }
    public int getDurationSeconds() {
        return durationMins*60;
    }
    public double getDistanceKm() {
        return distanceKm;
    }
    public double getDistanceMiles() {
        return distanceKm*KM_TO_MILES;
    }

    //Test harness
    public static void main(String[] args) {
        //--------- SETUP ---------
        Exercise exercise = new Exercise(ExerciseType.BADMINTON, 20, 20);
        //---------TEST A: CONVERSION GETTERS---------
        //KM to Miles
        boolean successA1 = false;
        if(exercise.getDistanceMiles() > 12.3 && exercise.getDistanceMiles() < 12.5){
            successA1 = true;
        }
        //Mins to Seconds
        boolean successA2 = false;
        if(exercise.getDurationSeconds() == 1200){
            successA2 = true;
        }
        //TEST RESULTS
        System.out.println("---------TEST A: RESULTS---------");
        System.out.println("KM/MILES: \t" + (successA1 ? "Pass" : "Fail"));
        System.out.println("MINS/SEC: \t" + (successA2 ? "Pass" : "Fail"));
    }
}
