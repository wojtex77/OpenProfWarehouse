package pl.wojciechsiwek.OpenProfWarehouse.materialStock;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "material_stock")
public class StockItem {
    @Id
    String signature;
    @NotNull String profile;
    @NotNull String material;
    @NotNull double profileLength;
    @NotNull int qty;
    @NotNull int availableQty;
    @NotNull String materialType;

    StockItem() {
    }

    StockItem(String signature, String profile, String material, double profileLength, int qty, int availableQty, String materialType) {
        this.signature = signature;
        this.profile = profile;
        this.material = material;
        this.profileLength = profileLength;
        this.qty = qty;
        this.availableQty = availableQty;
        this.materialType = materialType;
    }

    String getSignature() {
        return signature;
    }

    void setSignature(String signature) {
        this.signature = signature;
    }

    String getProfile() {
        return profile;
    }

    void setProfile(String profile) {
        this.profile = profile;
    }

    String getMaterial() {
        return material;
    }

    void setMaterial(String material) {
        this.material = material;
    }

    double getProfileLength() {
        return profileLength;
    }

    void setProfileLength(double profileLength) {
        this.profileLength = profileLength;
    }

    int getQty() {
        return qty;
    }

    void setQty(int qty) {
        this.qty = qty;
    }

    int getAvailableQty() {
        return availableQty;
    }

    void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    String getMaterialType() {
        return materialType;
    }

    void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Double.compare(stockItem.profileLength, profileLength) == 0 && qty == stockItem.qty && availableQty == stockItem.availableQty && signature.equals(stockItem.signature) && profile.equals(stockItem.profile) && material.equals(stockItem.material) && materialType.equals(stockItem.materialType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signature, profile, material, profileLength, qty, availableQty, materialType);
    }

    @Override
    public String toString() {
        return "StockItem{" + "signature='" + signature + '\'' + ", profile='" + profile + '\'' + ", material='" + material + '\'' + ", profileLength=" + profileLength + ", qty=" + qty + ", availableQty=" + availableQty + ", materialType='" + materialType + '\'' + '}';
    }
}
