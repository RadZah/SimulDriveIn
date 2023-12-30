package org.example;

public class Simul {

    private static Que_generator que_a;
    private static Que_generator que_b;


    public Simul(int max_val_interval, int max_val_serving){
        que_a = new Que_generator(max_val_interval, max_val_serving);
        que_b = new Que_generator(max_val_interval, max_val_serving);
    }

    public void init_ques(int initTime) {
        // start time
        this.que_a.setInitialTime(initTime);
        this.que_b.setInitialTime(initTime);
    }

    public void generateQuesWithRandomInterval(int noOfCustomers, String typeOfRandom) {
        // randomly generate queues
        this.que_a.generateQueCount(noOfCustomers, typeOfRandom);
        this.que_b.generateQueCount(noOfCustomers, typeOfRandom);
    }

    public void serve_ques(){
        // here we will process the queues

        this.que_a.processQue();
        this.que_b.processQue();
    }

    public void printResults(){
        // show results

        this.que_a.printResults();
        this.que_b.printResults();
    }

    public void saveToFile(String file) throws Exception{
        try {
            this.que_a.saveToFile(file);
        }catch (Exception e){
            throw e;
        }

    }


}
