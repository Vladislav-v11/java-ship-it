package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();
    static ParcelBox<StandartParcel> boxForStandartParcel = new ParcelBox<>(30);
    static ParcelBox<FragileParcel> boxForFragile = new ParcelBox<>(20);
    static ParcelBox<PerishableParcel> boxForPerishable = new ParcelBox<>(10);
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    getBoxItems();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Узнать статус доставки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        int timeToLive = 0;
        System.out.println("Какой тип посылки вы хотите отправить: 1 - стандартная, 2 - хрупкая, 3 - скоропортящаяся?");
        int typeParcel = Integer.parseInt(scanner.nextLine());
        if (typeParcel < 1 || typeParcel > 3) {
            System.out.println("Такого типа посылок нет");
            return;
        }

        System.out.println("Укажите массу посылки, масса может только быть положительным числом");
        int weight = Integer.parseInt(scanner.nextLine());
        if (weight <= 0) {
            System.out.println("Масса посылки может только быть положительным числом");
            return;
        }
        if (typeParcel == 1 && weight > boxForStandartParcel.getMaxWeight()
                || typeParcel == 2 && weight > boxForFragile.getMaxWeight()
                || typeParcel == 3 && weight > boxForPerishable.getMaxWeight()) {
            System.out.println("Превышен максимально возможный вес посылки");
            return;
        }

        if (typeParcel == 3) {
            System.out.println("Какой срок годности?");
            timeToLive = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("Что в посыллке?");
        String description = scanner.nextLine();

        System.out.println("Адрес по которому вы хотите отправить посылку?");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Какого числа вы отправляете посылку?");
        int day = Integer.parseInt(scanner.nextLine());

        switch (typeParcel) {
            case 1:
                StandartParcel standartParcel = new StandartParcel(description, weight, deliveryAddress, day);
                allParcels.add(standartParcel);
                boxForStandartParcel.addParcel(standartParcel);
                System.out.println("Посылка " + standartParcel.getDescription() + " принята для отправки");
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, day);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                boxForFragile.addParcel(fragileParcel);
                break;
            case 3:
                PerishableParcel perishableParcel = new
                        PerishableParcel(description, weight, deliveryAddress, day, timeToLive);
                allParcels.add(perishableParcel);
                boxForPerishable.addParcel(perishableParcel);
                break;

        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost(parcel.getWeight());
        }
        System.out.println("Общая стоимость всех доставок: " + sum);
    }

    private static void reportStatus() {
        for (Trackable parcel : trackableParcels) {
            Parcel p = (Parcel) parcel;
            System.out.println("Введите местоположение " + p.getDescription());
            String newLocation = scanner.nextLine();
            parcel.reportStatus(newLocation);
        }
    }
    private static void getBoxItems() {
        System.out.println("Содержимое какой коробки вы хотите увидеть: "
                + "1 - для стандартных посылок, 2 - для хрупких посылок, 3 - для скоропортящихся?");
        int typeBox = Integer.parseInt(scanner.nextLine());

        System.out.println("Коробка содержит: ");
        if (typeBox == 1) {
            boxForStandartParcel.getAllParcels();
        } else if (typeBox == 2) {
            boxForFragile.getAllParcels();
        } else if (typeBox == 3) {
            boxForPerishable.getAllParcels();
        } else {
            System.out.println("Такой коробки нет");
        }
    }
}


