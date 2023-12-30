package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;


public class Que_generator {

    private final int MID_VALUE_INTERVAL;
    private final int MID_VALUE_SERVING;

    public Que_generator(int mid_val_interval, int mid_val_serving){
        MID_VALUE_INTERVAL = mid_val_interval;
        MID_VALUE_SERVING = mid_val_serving;
    }

    ArrayList<Car> que = new ArrayList<>();
    private int initSimularTime;

    private int currentSimularTime;
    private int currentProcessingTime;


    public void setInitialTime(int init) {
        this.initSimularTime = init;
    }


    public void generateQueCount(int count, String typeOfRandom) {

        // generates que of randomly arriving cars
        int i = 0;
        while (i < count) {

            // randomly generate Car Arriving time
            Car car = new Car(i, this.MID_VALUE_INTERVAL, this.currentSimularTime, typeOfRandom);
//            Car car = new Car(i, this.MID_VALUE_INTERVAL, this.currentSimulatorTime);
            int simulationTimeAfterThisCar = car.getCurrentSimularTime();
            this.que.add(car);

            this.currentSimularTime = simulationTimeAfterThisCar;

            i++;

        }

    }


    public void generateQueTilTimeEnd(int maxSimularTime, String typeOfRandom){
        // generates que of randomly arriving cars

        int i = 0;
        int time = 0;
        while (time < maxSimularTime) {

            // randomly generate Car Arriving time
            Car car = new Car(i, this.MID_VALUE_INTERVAL, this.currentSimularTime, typeOfRandom);
            int simularTimeAfterThisCar = car.getCurrentSimularTime();
            this.que.add(car);
            this.currentSimularTime = simularTimeAfterThisCar;
            time = simularTimeAfterThisCar;
            if (time > maxSimularTime) {
                // if over time limit, remove from simulation
                this.que.remove(car);
            }
            i += 1;
        }
    }

    public void processQue(int lengthOfSimularTime, String typeOfRandom) {
        //  process que till the time is over

        this.setCurrentProcessTime(0);
        if (this.getCurrentProcessingTime() <= lengthOfSimularTime) {

            Iterator<Car> iterator = this.que.iterator();
            while (iterator.hasNext()) {

                Car car = iterator.next();
                int endServingCar = car.serveCar(this.MID_VALUE_SERVING, this.getCurrentProcessingTime(), lengthOfSimularTime, typeOfRandom);


                // Check if processing time exceeds the limit
                if ((this.getCurrentProcessingTime() > lengthOfSimularTime) ||
                        (endServingCar == -1)  // expected end of service after limit
                ) {
                    break; // Exit the loop if the limit is exceeded

                }else{
                    this.setCurrentProcessTime(endServingCar);
                }

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

        System.out.println("------------------------------------------------------------" +
                "------------------------------------------------------------");
        System.out.println("Car no." +
                "\t arrived at \t\t " +
                "\t after \t" +
                "\t waited for being served " +
                "\t served for " +
                "\t departed at \t" );
        System.out.println("------------------------------------------------------------" +
                        "------------------------------------------------------------");
        for (Car car : this.que) {
            int orderOfCar = car.getOrder();
            int waitingTime = car.getWaitingTime();
            int arrived = car.getArriveTime();
            int afterPrevious = car.getInterval();
            int wasServedFor = car.getServingTime();
            int departed = car.getEndServingTime();
            System.out.println("Car " + orderOfCar +
                    "\t arrived at \t " + arrived + ", " +
                    "\t after \t" + afterPrevious +
                    "\t waited for being served " + waitingTime +
                    "\t served for " + wasServedFor +
                    "\t departed at " + departed);
        }

    }

    public Path createFile(String fileName) throws IOException{

        // concatenate file path
        // TODO
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
        // save results to csv file

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
                ",waitedForBeBeingServed" +
                ",servedFor" +
                ",departedAt";
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
            int departed = car.getEndServingTime();
            lineOfData = orderOfCar +
                    "," + arrived +
                    "," + afterPrevious +
                    "," + waitingTime +
                    "," + wasServedFor +
                    "," + departed;
            try {
                Files.writeString(path, lineOfData + System.lineSeparator(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                throw ex;
            }
        }

    }

}
