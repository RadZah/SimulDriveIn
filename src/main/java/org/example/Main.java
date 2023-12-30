package org.example;

public class Main {

    public static void main(String[] args) {

        int MID_VALUE_ARRIVE_INTERVAL = 5 * 60;
        int MID_VALUE_SERVING = 5 * 60;
        int LENGTH_OF_SIMULAR_TIME = 48 * 60 * 60;  // 3600 is one hour

        String TYPE_OF_RANDOM_GENERATION = "Gaus";
//        String TYPE_OF_RANDOM_GENERATION = "Uniform";
//        String TYPE_OF_RANDOM_GENERATION = "Exponential";


        Simul sim = new Simul(MID_VALUE_ARRIVE_INTERVAL, MID_VALUE_SERVING);

        sim.init_ques();
        sim.generateQuesWithRandomInterval(LENGTH_OF_SIMULAR_TIME, TYPE_OF_RANDOM_GENERATION);

        sim.serve_ques(LENGTH_OF_SIMULAR_TIME, TYPE_OF_RANDOM_GENERATION);
        sim.printResults();

        try {
            sim.saveToFile("result.csv");
        }catch (Exception e){
            System.out.println("!!!! Result not saved !!! ");
            System.out.println(e.getMessage());
        }


    }
}