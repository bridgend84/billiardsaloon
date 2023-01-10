package org.example.data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

public abstract class BilliardTable {
    protected final BigDecimal hourlyPrice;
    protected final int id;
    protected static int counter = 0;
    private boolean isFree;
    private LocalTime canReserveAfter;
    public BilliardTable(BigDecimal price, LocalTime canReserveAfter) {
        this.hourlyPrice = price;
        this.id = counter++;
        this.isFree = true;
        this.canReserveAfter = canReserveAfter;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BilliardTable that = (BilliardTable) o;
        return id == that.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public BigDecimal getHourlyPrice() {
        return hourlyPrice;
    }
    public void setFree() {
        isFree = true;
    }
    public void setOccupied() {
        isFree = false;
    }
    public boolean isFree() {
        return isFree;
    }
    public boolean canReserveOnTime(LocalTime localTime) {
        return isFree && localTime.isAfter(canReserveAfter);
    }
}
