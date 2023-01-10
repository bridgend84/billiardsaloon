package org.example.data;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Reservation {
    private final LocalTime startTime;
    private LocalTime endTime;
    private Map<Consumable, Integer> consumption;
    private BilliardTable billiardTable;
    public Reservation(LocalTime startTime, BilliardTable billiardTable) {
        this.startTime = startTime;
        this.endTime = startTime;
        this.consumption = new HashMap<>();
        this.billiardTable = billiardTable;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public void addConsumable(Consumable consumable) {
        if (consumption.containsKey(consumable)) {
            consumption.put(consumable, consumption.get(consumable) + 1); // in the real life maybe better a separate line for every consumable
        } else {
            consumption.put(consumable, 1);
        }
    }
    public Map<Consumable, Integer> getConsumption() {
        return consumption;
    }
    public BigDecimal getTotalConsumptionPrice() {
        List<BigDecimal> priceList = new ArrayList<>();
        consumption.forEach((consumable, amount) -> priceList.add(consumable.getPrice().multiply(BigDecimal.valueOf(amount))));
        return priceList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
    public BigDecimal getReservePrice() {
        return billiardTable.getHourlyPrice().multiply(BigDecimal.valueOf(Duration.between(startTime, endTime).toHours()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(consumption, that.consumption) && Objects.equals(billiardTable, that.billiardTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, consumption, billiardTable);
    }

    public BilliardTable getBilliardTable() {
        return billiardTable;
    }
}
