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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getProfileLength() {
        return profileLength;
    }

    public void setProfileLength(double profileLength) {
        this.profileLength = profileLength;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
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
