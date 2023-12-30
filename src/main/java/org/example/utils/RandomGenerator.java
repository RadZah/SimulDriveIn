package org.example.utils;

import java.util.Random;

public class RandomGenerator {


//    private static Random rnd = new Random(922337203);
    private static Random rnd = new Random();


    public static int getNextUniformInt(int midValueInterval){
        float rndFloat = rnd.nextFloat();
        float interval = rndFloat * 2*midValueInterval ;  //  max is 1800 = 30 minutes
        int intervalInt = Math.round(interval);  //  max is 1800 = 30 minutes

        return intervalInt;
    }


    public static int getNextServingInt(int max_value_serving) {
        float rndFloat = rnd.nextFloat();
        float interval = rndFloat * max_value_serving ;  //  max is 1800 = 30 minutes
        int intervalInt = Math.round(interval);  //  max is 1800 = 30 minutes

        return intervalInt;
    }


    public static int getNextGaussianIntervalInt(int midValueInterval) {

        double stdDev = midValueInterval / 3;
        double rndNextGaussian =  rnd.nextGaussian();
        double res = midValueInterval + (stdDev * rndNextGaussian);
        return (int)res;
    }


    public static int getNextExponentialInt(int midValueInterval){
//        float lambda = 1 / midValueInterval;
        double lambda = 0.001;
        double rndDouble = rnd.nextDouble();
        double x = -Math.log(1 - rndDouble) / lambda;
        return (int)x;
    }


}
