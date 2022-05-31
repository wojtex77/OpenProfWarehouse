package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String orderNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date term;
    private String contrahent;


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public String getContrahent() {
        return contrahent;
    }

    public void setContrahent(String contrahent) {
        this.contrahent = contrahent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(orderNumber, order.orderNumber) && Objects.equals(term, order.term) && Objects.equals(contrahent, order.contrahent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber, term, contrahent);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderNumber='" + orderNumber + '\'' + ", term=" + term + ", contrahent='" + contrahent + '\'' + '}';
    }
}