package org.example.data;

import java.math.BigDecimal;
import java.util.Objects;

public class Consumable {
    protected String name;
    protected BigDecimal price;
    protected ConsumableType type;
    public Consumable(String name, BigDecimal price, ConsumableType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public ConsumableType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumable that = (Consumable) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type);
    }
}
