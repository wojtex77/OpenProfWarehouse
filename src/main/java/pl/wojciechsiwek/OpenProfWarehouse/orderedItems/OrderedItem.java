package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ordered_items")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String orderNumber;
    private Integer partId;
    private Integer qty;
    private Integer nestedQty;
    private Integer toNestQty;

    public OrderedItem() {
    }

    public OrderedItem(Integer id, String orderNumber, Integer partId, Integer qty) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.partId = partId;
        this.qty = qty;
        this.toNestQty = qty;
        this.nestedQty = 0;
    }

    OrderedItem(String orderNumber, Integer partId, Integer qty) {
        this.orderNumber = orderNumber;
        this.partId = partId;
        this.qty = qty;
        this.toNestQty = qty;
        this.nestedQty = 0;
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
    public void recalculateToNestQty(){
        this.toNestQty = this.qty - this.nestedQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItem that = (OrderedItem) o;
        return Objects.equals(id, that.id) && Objects.equals(orderNumber, that.orderNumber) && Objects.equals(partId, that.partId) && Objects.equals(qty, that.qty) && Objects.equals(nestedQty, that.nestedQty) && Objects.equals(toNestQty, that.toNestQty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber, partId, qty, nestedQty, toNestQty);
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", partId=" + partId +
                ", qty=" + qty +
                '}';
    }
}