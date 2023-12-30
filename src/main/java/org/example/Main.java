package org.example;

public class Main {

    public static void main(String[] args) {

        int MAX_VALUE_INTERVAL = 30 * 60;
        int MAX_VALUE_SERVING = 60 * 60;
        int INIT_TIME = 0;
        int NO_OF_CUSTOMERS = 200;
        String TYPE_OF_RANDOM_GENERATION = "Gaus";
//        String TYPE_OF_RANDOM_GENERATION = "Gaus";
//        String TYPE_OF_RANDOM_GENERATION = "Gaus";

        Simul sim = new Simul(MAX_VALUE_INTERVAL, MAX_VALUE_SERVING);
        sim.init_ques(INIT_TIME);
        sim.generateQuesWithRandomInterval(NO_OF_CUSTOMERS, TYPE_OF_RANDOM_GENERATION);
        sim.serve_ques();
        sim.printResults();

        try {
            sim.saveToFile("result.csv");
        }catch (Exception e){
            System.out.println("!!!! Result not saved !!! ");
            System.out.println(e.getMessage());
        }


    }
}