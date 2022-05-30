package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String stockSignature;
    private int repetition;

    public Reservation() {
    }

    public Reservation(String stockSignature, int repetition) {
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
}