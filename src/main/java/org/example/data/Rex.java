package org.example.data;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Rex extends BilliardTable {
    private static final BigDecimal REX_PER_HOUR = BigDecimal.valueOf(800);
    private static final LocalTime REX_RESERVE_AFTER = LocalTime.of(18, 0);
    public Rex() {
        super(REX_PER_HOUR, REX_RESERVE_AFTER);
    }
}
