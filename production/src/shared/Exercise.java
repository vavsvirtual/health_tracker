package shared;

/**
 * Project      : health_tracker
 * File         : Exercise.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores type of exercise, duration &/or distance
 */


public class Exercise{
    public enum exerciseType{
        //Individual sports
        RUNNING,
        CYCLING,
        SWIMMING,
        WALKING,

        //Group sports
        FOOTBALL,
        RUGBY,
        TENNIS,
        BADMINTON,
    }
    private final exerciseType type;
    private int durationMins;
    private float distanceKm;
    
    //Constructors
    public Exercise(exerciseType type, int durationMins){
        this.type = type;
        this.durationMins = durationMins;
    }
    public Exercise(exerciseType type, float distanceKm){
        this.type = type;
        this.distanceKm = distanceKm;

    }
    public Exercise(exerciseType type, int durationMins, float distanceKm){
        this.type = type;
        this.durationMins = durationMins;
        this.distanceKm = distanceKm;
    }

    //Getters
    public exerciseType getType() {
        return type;
    }
    public int getDurationMins() {
        return durationMins;
    }
    public int getDurationSeconds() {
        return durationMins;
    }
    public float getDistanceKm() {
        return distanceKm;
    }
    public float getDistanceMiles() {
        return (float) (distanceKm*0.6213712);
    }
}
