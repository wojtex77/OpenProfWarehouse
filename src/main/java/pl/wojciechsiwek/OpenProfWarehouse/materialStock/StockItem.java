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
    @NotNull double singleWeight;
    @NotNull double wholeWeight;
    @NotNull String materialType;

    StockItem() {
    }

    public StockItem(StockItem stockItem, double profileLength) {
        this.signature = stockItem.signature;
        this.profile = stockItem.profile;
        this.material = stockItem.material;
        this.profileLength = profileLength;
        this.qty = 1;
        this.availableQty = 1;
//        this.singleWeight = stockItem.singleWeight;
//        this.wholeWeight = stockItem.wholeWeight;
        this.materialType = stockItem.materialType;
    }

    public StockItem(String signature, String profile, String material, double profileLength, int qty, int availableQty, String materialType) {
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

    public void increaseBothQuantities() {
        this.qty++;
        this.availableQty++;
    }



    public boolean equalsExceptQuantites(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Double.compare(stockItem.profileLength, profileLength) == 0 && Double.compare(stockItem.singleWeight, singleWeight) == 0 && Double.compare(stockItem.wholeWeight, wholeWeight) == 0 && Objects.equals(signature, stockItem.signature) && Objects.equals(profile, stockItem.profile) && Objects.equals(material, stockItem.material) && Objects.equals(materialType, stockItem.materialType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Double.compare(stockItem.profileLength, profileLength) == 0 && qty == stockItem.qty && availableQty == stockItem.availableQty && Double.compare(stockItem.singleWeight, singleWeight) == 0 && Double.compare(stockItem.wholeWeight, wholeWeight) == 0 && Objects.equals(signature, stockItem.signature) && Objects.equals(profile, stockItem.profile) && Objects.equals(material, stockItem.material) && Objects.equals(materialType, stockItem.materialType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signature, profile, material, profileLength, qty, availableQty, singleWeight, wholeWeight, materialType);
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
