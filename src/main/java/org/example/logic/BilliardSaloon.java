package org.example.logic;

import org.example.data.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

public class BilliardSaloon {
    private Set<BilliardTable> billiardTables;
    private Set<Reservation> reservations;
    public BilliardSaloon(Set<BilliardTable> billiardTables) {
        this.billiardTables = billiardTables;
        this.reservations = new HashSet<>();
    }
    public void addTable(BilliardTable billiardTable) {
        billiardTables.add(billiardTable);
    }
    public void addReservation(LocalTime startTime) {
        BilliardTable currentBilliardTable = billiardTables
                .stream()
                .filter(billiardTable -> billiardTable.canReserveOnTime(startTime))
                .findFirst() // In a real situation the consumer can choose table, but now I think it's okay.
                .orElseThrow();
        reservations.add(new Reservation(startTime, currentBilliardTable));
        currentBilliardTable.setOccupied();
    }
    public void addConsumableToTable(Consumable consumable, BilliardTable billiardTable) {
        reservations
                .stream()
                .filter(reservation -> reservation.getBilliardTable().equals(billiardTable))
                .findFirst()
                .orElseThrow()
                .addConsumable(consumable);
    }
    public List<BigDecimal> generateInvoice(BilliardTable billiardTable, LocalTime endTime) {
        List<BigDecimal> invoice = new ArrayList<>();
        Reservation currentReservation = reservations
                .stream()
                .filter(reservation -> reservation.getBilliardTable().equals(billiardTable))
                .findFirst()
                .orElseThrow();
        currentReservation.setEndTime(endTime);
        invoice.add(currentReservation.getReservePrice());
        currentReservation
                .getConsumption()
                .forEach((consumable, amount) -> invoice.add(consumable.getPrice().multiply(BigDecimal.valueOf(amount))));
        billiardTable.setFree();
        reservations.remove(currentReservation);
        return invoice;
    }
    public List<BilliardTable> getAvailableTablesByType (Class<? extends BilliardTable> tableType, LocalTime currentTime) {
        return billiardTables
                .stream()
                .filter(billiardTable -> billiardTable.canReserveOnTime(currentTime))
                .filter(billiardTable -> billiardTable.getClass().equals(tableType)) // It doesn't work with instanceof, enum would be better for type matching?
                .toList();
    }
    public BilliardTable getBiggestConsumption() {
        return reservations
                .stream()
                .max(Comparator.comparing(Reservation::getTotalConsumptionPrice))
                .orElseThrow()
                .getBilliardTable();
    }
}
