package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class StockItemReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String stockSignature;
    private int repetition;

    public StockItemReservation() {
    }

    public StockItemReservation(String stockSignature, int repetition) {
        this.stockSignature = stockSignature;
        this.repetition = repetition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSignature() {
        return stockSignature;
    }

    public void setStockSignature(String stockSignature) {
        this.stockSignature = stockSignature;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItemReservation that = (StockItemReservation) o;
        return repetition == that.repetition && Objects.equals(id, that.id) && Objects.equals(stockSignature, that.stockSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stockSignature, repetition);
    }

    @Override
    public String toString() {
        return "StockItemReservation{" +
                "id=" + id +
                ", stockSignature='" + stockSignature + '\'' +
                ", repetition=" + repetition +
                '}';
    }
}