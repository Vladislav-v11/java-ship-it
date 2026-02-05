package ru.yandex.practicum.delivery;

import java.util.Objects;

public abstract class  Parcel {

    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;
    protected static final int BASE_COST_STANDARD = 2;
    protected static final int BASE_COST_PERISHABLE = 3;
    protected static final int BASE_COST_FRAGILE = 4;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public abstract int getBaseCost();

    public int calculateDeliveryCost(int weight) {
        if (weight <= 0)
            return -1;
        return weight * getBaseCost();
    }

    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + getDeliveryAddress());
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getWeight() {
        return weight;
    }

    public int getSendDay() {
        return sendDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return weight == parcel.weight && sendDay == parcel.sendDay && Objects.equals(description, parcel.description)
                && Objects.equals(deliveryAddress, parcel.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, deliveryAddress, sendDay);
    }
}