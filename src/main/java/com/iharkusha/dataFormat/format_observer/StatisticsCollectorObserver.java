package com.iharkusha.dataFormat.format_observer;

public class StatisticsCollectorObserver implements Observer {

    private Integer numOfNotifications = 0;

    @Override
    public void updateState() {
        numOfNotifications++;
    }

    public Integer getTotalNumOfNotifications() {
        return numOfNotifications;
    }
}
