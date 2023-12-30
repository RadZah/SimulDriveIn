package org.example.utils;

import java.util.Random;

public class RandomGenerator {


    private static Random rnd = new Random(922337203);


    public static int getNextIntervalInt(int maxValueInterval){
        float rndFloat = rnd.nextFloat();
        float interval = rndFloat * maxValueInterval ;  //  max is 1800 = 30 minutes
        int intervalInt = Math.round(interval);  //  max is 1800 = 30 minutes

        return intervalInt;
    }


    public static int getNextServingInt(int max_value_serving) {
        float rndFloat = rnd.nextFloat();
        float interval = rndFloat * max_value_serving ;  //  max is 1800 = 30 minutes
        int intervalInt = Math.round(interval);  //  max is 1800 = 30 minutes

        return intervalInt;
    }


    public static int getNextGaussianIntervalInt(int maxValueInterval) {
        double mean = maxValueInterval / 2;
        double stdDev = mean / 3;
        double rndNextGaussian =  rnd.nextGaussian();
        double res = mean + (stdDev * rndNextGaussian);
        return (int)res;
    }

    public static int getNextExponentialInt(int maxValueInterval, double lambda){
        double rndDouble = rnd.nextDouble();
        double x = -Math.log(1 - rndDouble) / lambda;
        return (int)x;
    }




}
