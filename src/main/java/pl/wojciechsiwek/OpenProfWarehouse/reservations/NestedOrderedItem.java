package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import javax.persistence.*;

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
}