package org.example;


import org.example.utils.RandomGenerator;

import java.time.LocalDateTime;

public class Car {


    private int order;
    private int interval;
    private int servingTime;
    private int arriveTime;

    private LocalDateTime incomeToStation;
    private LocalDateTime startBeeingServed;
    private LocalDateTime leaveStation;
    private int currentSimularTime;
    private int startServingTime;
    private int endServingTime;


    public int calculateStartServingTime(int currentSimularTime, int lengthOfSimularTime) {
        if (currentSimularTime > this.getArriveTime()){
            this.startServingTime = currentSimularTime;
        }else{
            this.startServingTime = this.getArriveTime();
        }
        return this.getStartServingTime();
    }

    public void setEndServingTime(int endServingTime) {
        this.endServingTime = endServingTime;
    }

    public int getInterval() {
        return interval;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getStartServingTime() {
        return this.startServingTime;
    }

    public int getEndServingTime() {
        int ret = this.endServingTime == 0 ? -1 : this.endServingTime;
        return ret;
    }

    public Car(int order, int intervalMid, int currentSimulationTime, String typeOfRandom){
        this.setOrder(order);
        int intervalTime = this.generateRandomInterval(intervalMid, typeOfRandom);
        this.setArriveTime(currentSimulationTime + intervalTime);
        this.setCurrentSimularTime(currentSimulationTime + intervalTime);
    }

    private void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    private void setCurrentSimularTime(int currentSimularTime){
        this.currentSimularTime = currentSimularTime;
    }


    private int generateRandomInterval(int mid_value_serving, String typeOfRandom) {
        //        int intervalTime = RandomGenerator.getNextIntervalInt(max_value_serving);

        int intervalTime;

        switch (typeOfRandom){
            case "Gaus":
                intervalTime = RandomGenerator.getNextGaussianIntervalInt(mid_value_serving);
                break;
            case "Exponential":
                intervalTime = RandomGenerator.getNextExponentialInt(mid_value_serving);
                break;
            case "Uniform":
                intervalTime = RandomGenerator.getNextUniformInt(mid_value_serving);
                break;
            default:
                System.out.println("Invalid random type: 86");
                throw new IllegalArgumentException("Invalid random type: " + typeOfRandom);

        }

        this.interval = intervalTime;
        return intervalTime;
    }

    private int calculateEndServingTime(int lengthOfSimularTime){

        int expectedEndServingTime = this.startServingTime + this.servingTime;
        if (expectedEndServingTime < lengthOfSimularTime){
            this.endServingTime = this.startServingTime + this.servingTime;
        }else{
            this.endServingTime = -1;
        }
        return this.endServingTime;
    }

    public int serveCar(int midValueServing, int currentProcessingTime, int lengthOfSimularTime, String typeOfRandom){
//        int randomServingTime = this.generateRandomServingTime(midValueServing);

        int randomServingTime;
        switch (typeOfRandom){
            case "Gaus":
                randomServingTime = RandomGenerator.getNextGaussianIntervalInt(midValueServing);
                break;
            case "Exponential":
                randomServingTime = RandomGenerator.getNextExponentialInt(midValueServing);
                break;
            case "Uniform":
                randomServingTime = RandomGenerator.getNextUniformInt(midValueServing);
                break;
            default:
                System.out.println("Invalid random type: 120");
                throw new IllegalArgumentException("Invalid random type: " + typeOfRandom);
        }

        this.servingTime = randomServingTime;
        int startServingTime = this.calculateStartServingTime(currentProcessingTime, lengthOfSimularTime);
        int endServingTime = this.calculateEndServingTime(lengthOfSimularTime);
        return endServingTime;
    }

    public int getServingTime(){
        int ret = this.servingTime == 0 ? -1 : this.servingTime;
        return ret;
    }

    public int getWaitingTime(){
        int waitingTime;

        if (this.getStartServingTime() == 0){
            waitingTime = -1;
        }else{
            waitingTime = this.getStartServingTime() - this.getArriveTime();
        }

        return waitingTime;

    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

     public int getCurrentSimularTime() {
        return this.currentSimularTime;
    }

}
