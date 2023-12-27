package org.example;


import java.time.LocalDateTime;

public class Car {


    private int order;
    private LocalDateTime incomeToStation;
    private LocalDateTime startBeeingServed;
    private LocalDateTime leaveStation;

    public Car(int order){
        this.setOrder(order);
        LocalDateTime now = LocalDateTime.now();
        setIncomeToStation(now);
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


}
