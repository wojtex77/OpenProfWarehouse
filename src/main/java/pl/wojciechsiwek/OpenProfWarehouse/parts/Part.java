package pl.wojciechsiwek.OpenProfWarehouse.parts;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String partName;
    String drawing;
    String article;

    String contrahent;
    String material;
    String profile;

    double profileLength;

    public Part() {
    }

    public int getId() {
        return id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getContrahent() {
        return contrahent;
    }

    public void setContrahent(String contrahent) {
        this.contrahent = contrahent;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public double getProfileLength() {
        return profileLength;
    }

    public void setProfileLength(double profileLength) {
        this.profileLength = profileLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;

        Part part = (Part) o;

        if (id != part.id) return false;
        if (Double.compare(part.profileLength, profileLength) != 0) return false;
        if (partName != null ? !partName.equals(part.partName) : part.partName != null) return false;
        if (drawing != null ? !drawing.equals(part.drawing) : part.drawing != null) return false;
        if (article != null ? !article.equals(part.article) : part.article != null) return false;
        if (contrahent != null ? !contrahent.equals(part.contrahent) : part.contrahent != null) return false;
        if (material != null ? !material.equals(part.material) : part.material != null) return false;
        return profile != null ? profile.equals(part.profile) : part.profile == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (partName != null ? partName.hashCode() : 0);
        result = 31 * result + (drawing != null ? drawing.hashCode() : 0);
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (contrahent != null ? contrahent.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        temp = Double.doubleToLongBits(profileLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", partName='" + partName + '\'' +
                ", drawing='" + drawing + '\'' +
                ", article='" + article + '\'' +
                ", contrahent='" + contrahent + '\'' +
                ", material='" + material + '\'' +
                ", profile='" + profile + '\'' +
                ", profileLength=" + profileLength +
                '}';
    }
}
