package org.example.data;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Pool extends BilliardTable {
    private static final BigDecimal POOL_PER_HOUR = BigDecimal.valueOf(1200);
    private static final LocalTime POOL_RESERVE_AFTER = LocalTime.of(0,0);
    public Pool() {
        super(POOL_PER_HOUR, POOL_RESERVE_AFTER);
    }
}
