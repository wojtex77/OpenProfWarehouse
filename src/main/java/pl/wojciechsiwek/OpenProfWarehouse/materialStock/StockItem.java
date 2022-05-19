package pl.wojciechsiwek.OpenProfWarehouse.materialStock;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
    @NotNull double singleWeight;
    @NotNull double wholeWeight;
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

    public double getSingleWeight() {
        return singleWeight;
    }

    public void setSingleWeight(double singleWeight) {
        this.singleWeight = singleWeight;
    }

    public double getWholeWeight() {
        return wholeWeight;
    }

    public void setWholeWeight(double wholeWeight) {
        this.wholeWeight = wholeWeight;
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
    public void decreaseAvailableQty() {
        this.availableQty--;
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
        if (!(o instanceof StockItem)) return false;

        StockItem stockItem = (StockItem) o;

        if (Double.compare(stockItem.profileLength, profileLength) != 0) return false;
        if (qty != stockItem.qty) return false;
        if (availableQty != stockItem.availableQty) return false;
        if (Double.compare(stockItem.singleWeight, singleWeight) != 0) return false;
        if (Double.compare(stockItem.wholeWeight, wholeWeight) != 0) return false;
        if (signature != null ? !signature.equals(stockItem.signature) : stockItem.signature != null) return false;
        if (profile != null ? !profile.equals(stockItem.profile) : stockItem.profile != null) return false;
        if (material != null ? !material.equals(stockItem.material) : stockItem.material != null) return false;
        return materialType != null ? materialType.equals(stockItem.materialType) : stockItem.materialType == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = signature != null ? signature.hashCode() : 0;
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        temp = Double.doubleToLongBits(profileLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + qty;
        result = 31 * result + availableQty;
        temp = Double.doubleToLongBits(singleWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(wholeWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (materialType != null ? materialType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "signature='" + signature + '\'' +
                ", profile='" + profile + '\'' +
                ", material='" + material + '\'' +
                ", profileLength=" + profileLength +
                ", qty=" + qty +
                ", availableQty=" + availableQty +
                ", singleWeight=" + singleWeight +
                ", wholeWeight=" + wholeWeight +
                ", materialType='" + materialType + '\'' +
                '}';
    }
}
