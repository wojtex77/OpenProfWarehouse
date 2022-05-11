package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import java.util.Objects;

public class OrderedItemsExtended {

    private int id;
    private int partId;
    private String partName;
    private int qty;
    private String profile;
    private double profileLength;
    private String material;

    OrderedItemsExtended(int id, int partId, String partName, int qty, String profile, double profileLength, String material) {
        this.id = id;
        this.partId = partId;
        this.partName = partName;
        this.qty = qty;
        this.profile = profile;
        this.profileLength = profileLength;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public String getPartName() {
        return partName;
    }

    public int getQty() {
        return qty;
    }

    public String getProfile() {
        return profile;
    }

    public int getProfileLength() {
        return (int) profileLength;
    }

    public String getMaterial() {
        return material;
    }

    public int getPartId() {
        return partId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItemsExtended that = (OrderedItemsExtended) o;
        return id == that.id && qty == that.qty && profileLength == that.profileLength && Objects.equals(partName, that.partName) && Objects.equals(profile, that.profile) && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partName, qty, profile, profileLength, material);
    }

    @Override
    public String toString() {
        return "OrderedItemsExtended{" + "id=" + id + ", partName='" + partName + '\'' + ", qty=" + qty + ", profile='" + profile + '\'' + ", profileLength=" + profileLength + ", material='" + material + '\'' + '}';
    }
}
