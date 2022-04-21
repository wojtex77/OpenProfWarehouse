package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
public class MaterialShape {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String name;
    @NotNull
    @Positive
    private Double area;

    MaterialShape() {
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Double getArea() {
        return area;
    }

    void setArea(Double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "MaterialShape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialShape that = (MaterialShape) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, area);
    }
}
