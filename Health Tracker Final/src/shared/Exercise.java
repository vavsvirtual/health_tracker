package shared;

import java.io.Serializable;

/**
 * Project      : health_tracker
 * File         : Exercise.java
 * Last Edit    : 12/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores type of exercise, duration &/or distance
 */


public class Exercise implements Serializable {
    private static final long serialVersionUID = 8392101L;
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
    //To String
    public String toString(){
        String stringForm = type.toString() + " for ";
        if(distanceKm > 0){
            stringForm = stringForm + distanceKm + "Km";
        }if(durationMins > 0){
            stringForm = stringForm + ((distanceKm > 0) ? " in ": "");
            stringForm = stringForm + durationMins + " Minutes";
        }
        return stringForm;
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
