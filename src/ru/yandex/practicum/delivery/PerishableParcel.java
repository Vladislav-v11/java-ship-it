package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {

    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        boolean b;
        if (getSendDay() + timeToLive >= currentDay) {
            b = false;
        } else {
            b = true;
        }
        return b;
    }

    @Override
    public int getBaseCost() {
        return BASE_COST_PERISHABLE;
    }

    public int getTimeToLive() {
        return timeToLive;
    }
}
