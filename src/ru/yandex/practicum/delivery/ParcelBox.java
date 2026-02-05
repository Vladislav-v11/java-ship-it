package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private List<T> box = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (parcel.getWeight() <= 0 || parcel.getWeight() > getMaxWeight()) {
            return;
        }
        box.add(parcel);
    }

    public void getAllParcels() {
        if (box.isEmpty()) {
            System.out.println("Коробка пуста");
        } else {
            for (T parcel : box) {
                System.out.println(parcel.getDescription());
            }
        }
    }

    public boolean getParcel(T parcel) {
        boolean b = false;
        for (T p : box) {
            if (p.equals(parcel)) {
                b = true;
                break;
            }
        }
        return b;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}