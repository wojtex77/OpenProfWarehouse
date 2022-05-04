package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
        if (!(o instanceof Contrahent)) return false;

        Contrahent that = (Contrahent) o;

        if (id != that.id) return false;
        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;
        return fullName != null ? fullName.equals(that.fullName) : that.fullName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
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
