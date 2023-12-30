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
    private int currentSimulatorTime;
    private int startServingTime;
    private int endServingTime;

    public void generateRandomServingTime(int maxValueServing) {
//        int servingTime = RandomGenerator.getNextServingInt(maxValueServing);
        int servingTime = RandomGenerator.getNextGaussianIntervalInt(maxValueServing);
        this.servingTime = servingTime;
    }

    public int calculateStartServingTime(int currentSimulatorTime) {
        if (currentSimulatorTime > this.getArriveTime()){
            this.startServingTime = currentSimulatorTime;
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
        return endServingTime;
    }

    public Car(int order, int intervalMax, int currentSimulationTime, String typeOfRandom){
        this.setOrder(order);
        int intervalTime = this.generateRandomInterval(intervalMax, typeOfRandom);
        this.setArriveTime(currentSimulationTime + intervalTime);
        this.setCurrentSimulatorTime(currentSimulationTime + intervalTime);
    }

    private void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    private void setCurrentSimulatorTime(int currentSimulatorTime){
        this.currentSimulatorTime = currentSimulatorTime;
    }


    private int getIntervalTime() {
        return this.interval;
    }


//    private double generateRandomNormalDistribution(int mean, int stdDev){
//
//        double randomNumber = mean + stdDev * RandomGenerator.nextGaussian();
//        return randomNumber;
//
//    }


    private int generateRandomInterval(int max_value_serving, String typeOfRandom) {

        //        int intervalTime = RandomGenerator.getNextIntervalInt(max_value_serving);
        int intervalTime;

        switch (typeOfRandom){
            case "Gaus":
                intervalTime = RandomGenerator.getNextGaussianIntervalInt(max_value_serving);
                break;
//            case "Exponential":
//                int intervalTime = RandomGenerator.getNextExponentialInt(max_value_serving);
//                break;
            default:
                intervalTime = RandomGenerator.getNextIntervalInt(max_value_serving);
                break;
        }



        this.interval = intervalTime;
        return intervalTime;
    }

    private int calculateEndServingTime(){
        this.endServingTime = this.startServingTime + this.servingTime;
        return this.endServingTime;
    }

    public int serveCar(int maxValueServing, int currentProcessingTime){
        this.generateRandomServingTime(maxValueServing);
//        this.getNextGaussianIntervalInt(maxValueServing);

        this.calculateStartServingTime(currentProcessingTime);
        return this.calculateEndServingTime();
    }

    public int getServingTime(){
        return this.servingTime;
    }

    public int getWaitingTime(){

        //  arriving_time - serving_time
//        int waitingTime = this.getEndServingTime() - this.getArriveTime();
        int waitingTime = this.getStartServingTime() - this.getArriveTime();
        return waitingTime;

    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public LocalDateTime getIncomeToStation() {
        return incomeToStation;
    }

    public void setIncomeToStation(LocalDateTime incomeToStation) {
        this.incomeToStation = incomeToStation;
    }

    public LocalDateTime getStartBeeingServed() {
        return startBeeingServed;
    }

    public void setStartBeeingServed(LocalDateTime startBeeingServed) {
        this.startBeeingServed = startBeeingServed;
    }

    public LocalDateTime getLeaveStation() {
        return leaveStation;
    }

    public void setLeaveStation(LocalDateTime leaveStation) {
        this.leaveStation = leaveStation;
    }


    public int getCurrentSimulatorTime() {
        return this.currentSimulatorTime;
    }
}
