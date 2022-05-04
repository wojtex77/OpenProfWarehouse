package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "material_grades")
public class MaterialGrade {


    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialGrade)) return false;

        MaterialGrade that = (MaterialGrade) o;

        if (id != that.id) return false;
        if (!fullName.equals(that.fullName)) return false;
        return density != null ? density.equals(that.density) : that.density == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fullName.hashCode();
        result = 31 * result + (density != null ? density.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MaterialGrade{" + "id=" + id + ", fullName='" + fullName + '\'' + ", density=" + density + '}';
    }
}
