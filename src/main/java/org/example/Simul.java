package org.example;

public class Simul {

    private static Que_generator que_a;
    private static Que_generator que_b;


    public Simul(int mid_val_interval, int mid_val_serving){
        que_a = new Que_generator(mid_val_interval, mid_val_serving);
        que_b = new Que_generator(mid_val_interval, mid_val_serving);
    }

    public void init_ques() {
        // start time
        this.que_a.setInitialTime(0);
        this.que_b.setInitialTime(0);
    }

//    public void generateQuesWithRandomInterval(int noOfCustomers, String typeOfRandom) {
    public void generateQuesWithRandomInterval(int lengthOfSimularTime, String typeOfRandom) {
        // randomly generate queues
//        this.que_a.generateQueCount(noOfCustomers, typeOfRandom);
        this.que_a.generateQueTilTimeEnd(lengthOfSimularTime, typeOfRandom);
//        this.que_b.generateQueCount(noOfCustomers, typeOfRandom);
        this.que_b.generateQueTilTimeEnd(lengthOfSimularTime, typeOfRandom);
    }



    public void serve_ques(int lengthOfSimularTime, String typeOfRandom){
        // here we will process the queues

        this.que_a.processQue(lengthOfSimularTime, typeOfRandom);
        this.que_b.processQue(lengthOfSimularTime, typeOfRandom);
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
