package shared;

/**
 * Project      : health_tracker
 * File         : Weight.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores a Weight in KG, while providing methods
 *                  to assist in data manipulation for health tracking
 */

public class Weight {
    public static final double KG_TO_Pounds = 2.204623;
    private final double weightKg;

    //
    public enum BMIRank{
        UNDERWEIGHT(Double.MIN_VALUE, 18.5),
        NORMAL(18.5, 25),
        OVERWEIGHT(25,30),
        OBESE(30,40),
        VERY_OBESE(40, Double.MAX_VALUE);
        private final double lowerBound, upperBound;
        private BMIRank(double lowerBound, double upperBound){
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
        public double getLowerBound() {
            return lowerBound;
        }
        public double getUpperBound() {
            return upperBound;
        }
    }


    //Constructor (metric)
    public Weight(double weightKg){
        this.weightKg = weightKg;
    }
    //Constructor (imperial)
    public Weight(int pounds){
        this(pounds/KG_TO_Pounds);
    }
    //Constructor (imperial)
    public Weight(int stone, int pounds){
        this((14*stone) + pounds);
    }

    //Getters
    public int getWeightPounds(){
        return Math.toIntExact(Math.round(weightKg * KG_TO_Pounds));
    }
    public double getWeightKg(){
        return weightKg;
    }

    //Static methods
    public static double calculateBMI(double weightKg, int heightCm){
        return weightKg/(heightCm/100d);
    }
    public static BMIRank evalBMI(double bmi){
        BMIRank evalRank = BMIRank.NORMAL;
        for(BMIRank rank : BMIRank.values()){
            if(bmi >= rank.getLowerBound() && bmi < rank.getUpperBound()){
                evalRank = rank;
                break;
            }
        }
        return evalRank;
    }

}
