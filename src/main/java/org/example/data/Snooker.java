package org.example.data;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Snooker extends BilliardTable{
    private static final BigDecimal SNOOKER_PER_HOUR = BigDecimal.valueOf(1600);
    private static final LocalTime SNOOKER_RESERVE_AFTER = LocalTime.of(18, 0);
    public Snooker() {
        super(SNOOKER_PER_HOUR, SNOOKER_RESERVE_AFTER);
    }
}
