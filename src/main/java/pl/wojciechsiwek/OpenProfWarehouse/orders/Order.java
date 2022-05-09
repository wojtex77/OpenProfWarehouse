package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public String getTerm() {
        return convertToLocalDateViaInstant(term);
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

    public String convertToLocalDateViaInstant(Date dateToConvert) {

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        if (term != null ? !term.equals(order.term) : order.term != null) return false;
        return contrahent != null ? contrahent.equals(order.contrahent) : order.contrahent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (contrahent != null ? contrahent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderNumber='" + orderNumber + '\'' + ", term=" + term + ", contrahent='" + contrahent + '\'' + '}';
    }
}