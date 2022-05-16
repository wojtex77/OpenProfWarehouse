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
    private String orderNumber = null;
    private String article = null;
    private String drawing = null;
    private double weight;

    OrderedItemsExtended(int id, int partId, String partName, int qty, String profile, double profileLength, String material) {
        this.id = id;
        this.partId = partId;
        this.partName = partName;
        this.qty = qty;
        this.profile = profile;
        this.profileLength = profileLength;
        this.material = material;
    }

    OrderedItemsExtended(int id, int partId, String partName, int qty, String profile, double profileLength, String material, String orderNumber, String article, String drawing, double weight) {
        this.id = id;
        this.partId = partId;
        this.partName = partName;
        this.qty = qty;
        this.profile = profile;
        this.profileLength = profileLength;
        this.material = material;
        this.orderNumber = orderNumber;
        this.article = article;
        this.drawing = drawing;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
