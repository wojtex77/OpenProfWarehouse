package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "contrahents")
public class Contrahent {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique = true)
    private String alias;

    @NotNull
    @Column(unique = true)
    private String fullName;


    public Contrahent() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrahent that = (Contrahent) o;
        return id == that.id && Objects.equals(alias, that.alias) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias, fullName);
    }

    @Override
    public String toString() {
        return "Contrahent{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
