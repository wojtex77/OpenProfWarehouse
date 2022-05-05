package pl.wojciechsiwek.OpenProfWarehouse.parts;

import javax.persistence.*;
import java.util.Objects;

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
    double weight;

    public Part() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id && Double.compare(part.profileLength, profileLength) == 0 && Double.compare(part.weight, weight) == 0 && Objects.equals(partName, part.partName) && Objects.equals(drawing, part.drawing) && Objects.equals(article, part.article) && Objects.equals(contrahent, part.contrahent) && Objects.equals(material, part.material) && Objects.equals(profile, part.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partName, drawing, article, contrahent, material, profile, profileLength, weight);
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
                ", weight=" + weight +
                '}';
    }
}
