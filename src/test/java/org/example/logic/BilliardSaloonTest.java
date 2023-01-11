package org.example.logic;

import org.example.data.Consumable;
import org.example.data.ConsumableType;
import org.example.data.Pool;
import org.example.data.Snooker;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BilliardSaloonTest {
    Snooker snooker = new Snooker();
    Pool pool = new Pool();
    BilliardSaloon billiardSaloon = new BilliardSaloon(Set.of(snooker, pool));

    @Test
    void getAvailableTablesByType() {
        assertEquals(billiardSaloon.getAvailableTablesByType(Pool.class, LocalTime.of(17, 59)), List.of(pool));
    }

    @Test
    void getBiggestConsumption() {
        billiardSaloon.addReservation(LocalTime.of(17, 59));
        billiardSaloon.addReservation(LocalTime.of(18, 1));
        billiardSaloon.addConsumableToTable(new Consumable("beer", BigDecimal.TEN, ConsumableType.BEVERAGE), pool);
        billiardSaloon.addConsumableToTable(new Consumable("beer", BigDecimal.TEN, ConsumableType.BEVERAGE), pool);
        billiardSaloon.addConsumableToTable(new Consumable("beer", BigDecimal.TEN, ConsumableType.BEVERAGE), snooker);
        assertEquals(pool, billiardSaloon.getBiggestConsumption());
    }

    @Test
    void generateInvoice() {
        billiardSaloon.addReservation(LocalTime.of(17, 59));
        billiardSaloon.addReservation(LocalTime.of(18, 1));
        billiardSaloon.addConsumableToTable(new Consumable("beer", BigDecimal.TEN, ConsumableType.BEVERAGE), pool);
        billiardSaloon.addConsumableToTable(new Consumable("beer", BigDecimal.TEN, ConsumableType.BEVERAGE), pool);
        billiardSaloon.addConsumableToTable(new Consumable("beer", BigDecimal.TEN, ConsumableType.BEVERAGE), snooker);
        assertEquals(List.of(BigDecimal.valueOf(1200), BigDecimal.valueOf(20)), billiardSaloon.generateInvoice(pool, LocalTime.of(19, 1)));
    }
}