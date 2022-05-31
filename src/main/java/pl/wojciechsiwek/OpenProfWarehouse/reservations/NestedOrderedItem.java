package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nested_ordered_items")
public class NestedOrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer reservationId;
    private Integer orderedItemId;
    private Integer nestedQty;

    NestedOrderedItem(Integer reservationId, Integer orderedItemId, Integer nestedQty) {
        this.reservationId = reservationId;
        this.orderedItemId = orderedItemId;
        this.nestedQty = nestedQty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getOrderedItemId() {
        return orderedItemId;
    }

    public void setOrderedItemId(Integer orderedItemId) {
        this.orderedItemId = orderedItemId;
    }

    public Integer getNestedQty() {
        return nestedQty;
    }

    public void setNestedQty(Integer nestedQty) {
        this.nestedQty = nestedQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedOrderedItem that = (NestedOrderedItem) o;
        return Objects.equals(id, that.id) && Objects.equals(reservationId, that.reservationId) && Objects.equals(orderedItemId, that.orderedItemId) && Objects.equals(nestedQty, that.nestedQty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservationId, orderedItemId, nestedQty);
    }

    @Override
    public String toString() {
        return "NestedOrderedItem{" +
                "id=" + id +
                ", reservationId=" + reservationId +
                ", orderedItemId=" + orderedItemId +
                ", nestedQty=" + nestedQty +
                '}';
    }
}