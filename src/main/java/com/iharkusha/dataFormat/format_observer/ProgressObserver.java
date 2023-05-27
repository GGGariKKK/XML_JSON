package com.iharkusha.dataFormat.format_observer;

public class ProgressObserver implements Observer {
    Integer totalAmount;
    Integer current = 0;

    public ProgressObserver(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public void updateState() {
        int percentage = (int) Math.round(1.0 * ++current / totalAmount * 100);
        if (percentage <= 100)
            System.out.printf("***Progress: %s%% ***%n", percentage);
    }
}
