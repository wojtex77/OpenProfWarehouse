package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "material_grades")
public class MaterialGrade {


    private @Id
    @GeneratedValue
    int id;
    private String alias;
    private String fullName;
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
