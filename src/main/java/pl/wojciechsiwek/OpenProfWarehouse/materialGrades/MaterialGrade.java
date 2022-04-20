package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "material_grades")
public class MaterialGrade {


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique = true)
    public String alias;

    private String fullName;
    @Min(0)
    private Float density;

    MaterialGrade() {
    }

    public Float getDensity() {
        return density;
    }

    public void setDensity(Float density) {
        this.density = density;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialGrade that = (MaterialGrade) o;
        return id == that.id && alias.equals(that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias);
    }

    @Override
    public String toString() {
        return "MaterialGrade{" + "id=" + id + ", alias='" + alias + '\'' + '}';
    }
}
