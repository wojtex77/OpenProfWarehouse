package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ordered_items")
public class OrderedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String orderNumber;
    private Integer partId;
    private Integer qty;
    private Integer nestedQty;
    private Integer toNestQty;

    public OrderedItems() {
    }

    public OrderedItems(Integer id, String orderNumber, Integer partId, Integer qty) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.partId = partId;
        this.qty = qty;
    }

    OrderedItems(String orderNumber, Integer partId, Integer qty) {
        this.orderNumber = orderNumber;
        this.partId = partId;
        this.qty = qty;
    }

    String getOrderNumber() {
        return orderNumber;
    }

    void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    Integer getPartId() {
        return partId;
    }

    void setPartId(Integer partId) {
        this.partId = partId;
    }

    Integer getQty() {
        return qty;
    }

    void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNestedQty() {
        return nestedQty;
    }

    public void setNestedQty(Integer nestedQty) {
        this.nestedQty = nestedQty;
    }

    public Integer getToNestQty() {
        return toNestQty;
    }

    public void setToNestQty(Integer toNestQty) {
        this.toNestQty = toNestQty;
    }

    public void changeNestedQtyByValue(int nestedQty) {
        this.nestedQty += nestedQty;
        toNestQty = qty - nestedQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItems that = (OrderedItems) o;
        return Objects.equals(id, that.id) && Objects.equals(orderNumber, that.orderNumber) && Objects.equals(partId, that.partId) && Objects.equals(qty, that.qty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber, partId, qty);
    }

    @Override
    public String toString() {
        return "OrderedItems{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", partId=" + partId +
                ", qty=" + qty +
                '}';
    }
}