package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {
    //DeliveryApp d = new DeliveryApp();
    @Test
    public void shouldBeReturn2WhenStandartParcelAndWeight1() {
        StandartParcel p = new StandartParcel("a", 1, "b", 1);
        assertEquals(2, p.calculateDeliveryCost(p.getWeight()));
    }
    @Test
    public void shouldBeNegativeWhenStandartParcelAndWeight0(){
        StandartParcel p = new StandartParcel("a", 0, "b", 1);
        assertTrue(p.calculateDeliveryCost(p.getWeight()) < 0);
    }
    @Test
    public void shouldBeReturn3WhenFragileParcelAndWeight1() {
        FragileParcel p = new FragileParcel("a", 1, "b", 1);
        assertEquals(4, p.calculateDeliveryCost(p.getWeight()));
    }
    @Test
    public void shouldBeNegativeWhenFragileParcelAndWeight0() {
        FragileParcel p = new FragileParcel("a", 0, "b", 1);
        assertTrue(p.calculateDeliveryCost(p.getWeight()) < 0);
    }
    @Test
    public void shouldBeReturn4WhenPerishableParcelAndWeight1() {
        PerishableParcel p = new PerishableParcel("a", 1, "b", 1, 5);
        assertEquals(3, p.calculateDeliveryCost(p.getWeight()));
    }
    @Test
    public void shouldBeNegativeWhenPerishableParcelAndWeight0() {
        PerishableParcel p = new PerishableParcel("a", 0, "b", 1, 5);
        assertTrue(p.calculateDeliveryCost(p.getWeight()) < 0);
    }
    @Test
    public void shouldBeReturnFalseWhenTimeToLive5AndCurrentDay5() {
        PerishableParcel p = new PerishableParcel("a", 1, "b", 1, 5);
        assertFalse(p.isExpired(6));
    }
    @Test
    public void shouldBeReturnTrueWhenTimeToLive5AndCurrentDay7() {
        PerishableParcel p = new PerishableParcel("a", 1, "b", 1, 5);
        assertTrue(p.isExpired(7));
    }
    @Test
    public void shouldBeReturnTrueWhenAddParcelLessThanBox() {
        ParcelBox<Parcel> b = new ParcelBox<>(2);
        StandartParcel p = new StandartParcel("a", 1, "b", 1);
        b.addParcel(p);
        assertTrue(b.getParcel(p));
    }
    @Test
    public void shouldBeReturnFalseWhenAddParcelOverThenBox() {
        ParcelBox<Parcel> b = new ParcelBox<>(2);
        StandartParcel p = new StandartParcel("a", 3, "b", 1);
        b.addParcel(p);
        assertFalse(b.getParcel(p));
    }
    @Test
    public void shouldBeReturnTrueWhenAddParcelEqualsBox() {
        ParcelBox<Parcel> b = new ParcelBox<>(2);
        StandartParcel p = new StandartParcel("a", 2, "b", 1);
        b.addParcel(p);
        assertTrue(b.getParcel(p));
    }
}
