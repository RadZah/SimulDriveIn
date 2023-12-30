package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class Que_generator {

    private final int MAX_VALUE_INTERVAL;
    private final int MAX_VALUE_SERVING;

    public Que_generator(int max_val_interval, int max_val_serving){
        MAX_VALUE_INTERVAL = max_val_interval;
        MAX_VALUE_SERVING = max_val_serving;
    }

    ArrayList<Car> que = new ArrayList<>();
    private int initSimulationTime;

    private int currentSimulatorTime;
    private int currentProcessingTime;


    public void setInitialTime(int init) {
        this.initSimulationTime = init;
    }


    public void generateQueCount(int count, String typeOfRandom) {

        // generates que of randomly arriving cars
        int i = 0;
        while (i < count) {

            // randomly generate Car Arriving time
            Car car = new Car(i, this.MAX_VALUE_INTERVAL, this.currentSimulatorTime, typeOfRandom);
//            Car car = new Car(i, this.MID_VALUE_INTERVAL, this.currentSimulatorTime);
            int simulationTimeAfterThisCar = car.getCurrentSimulatorTime();
            this.que.add(car);

            this.currentSimulatorTime = simulationTimeAfterThisCar;

            i++;

        }

    }


    public void processQue() {

//        this.setcurrentProcessingTime = initProcessTime;
        int firstCarArrive = this.que.get(0).getArriveTime();

        this.setCurrentProcessTime(firstCarArrive);

        if (this.que != null) {
            for (Car car : this.que) {
//                System.out.println(car.something());
                int endServingCar = car.serveCar(this.MAX_VALUE_SERVING, this.getCurrentProcessingTime());
                this.setCurrentProcessTime(endServingCar);
            }
        } else {
            System.out.println("Queue is null. Generate the queue first.");
        }

    }

    private int getCurrentProcessingTime() {
        return this.currentProcessingTime;
    }

    private void setCurrentProcessTime(int currentProcessingTime) {
        this.currentProcessingTime = currentProcessingTime;
    }


    public void printResults() {

        for (Car car : this.que) {
            int orderOfCar = car.getOrder();
            int waitingTime = car.getWaitingTime();
            int arrived = car.getArriveTime();
            int afterPrevious = car.getInterval();
            int wasServedFor = car.getServingTime();
            int departured = car.getEndServingTime();
            System.out.println("Car " + orderOfCar +
                    "\t arrived at \t " + arrived + ", " +
                    "\t after \t" + afterPrevious +
                    "\t waited for " + waitingTime +
                    "\t served for " + wasServedFor +
                    "\t departured at " + departured);
        }

    }

    public Path createFile(String fileName) throws IOException{

        // concatenate file path
        String filePath = "C:\\osu\\6_1\\XMOSM\\Semestral_work\\"+fileName;
        Path path = Paths.get(filePath);

        // delete file if exists
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new IOException("An error occurred while deleting the already existing file " + path, e);
            }
        }

        // create brand new file
        try {
            Files.createFile(path);
            System.out.println("File created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("An error occurred while creating the file" + path, e);
        }

        return path;

    }

    public void saveToFile(String fileName) throws Exception {

        Path path = null;

        // create new file (and delete previous, if exists)
        try {
            path = this.createFile(fileName);
        }catch (Exception e){
            throw e;
        }

        // create header
        String lineOfData = "order" +
                ",arrivedAt"+
                ",afterPrevious" +
                ",waitedForBeforeStartBeeingServed" +
                ",wasServedFor" +
                ",departuredAt";
        try {
            Files.writeString(path, lineOfData + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw ex;
        }

        // add all records
        for (Car car : this.que) {
            int orderOfCar = car.getOrder();
            int waitingTime = car.getWaitingTime();
            int arrived = car.getArriveTime();
            int afterPrevious = car.getInterval();
            int wasServedFor = car.getServingTime();
            int departured = car.getEndServingTime();
            lineOfData = orderOfCar +
                    "," + arrived +
                    "," + afterPrevious +
                    "," + waitingTime +
                    "," + wasServedFor +
                    "," + departured;
            try {
                Files.writeString(path, lineOfData + System.lineSeparator(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                throw ex;
            }
        }


    }

}
