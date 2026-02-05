package ru.yandex.practicum.delivery;

public class StandartParcel extends Parcel {

    public StandartParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getBaseCost() {
        return BASE_COST_STANDARD;
    }
}
