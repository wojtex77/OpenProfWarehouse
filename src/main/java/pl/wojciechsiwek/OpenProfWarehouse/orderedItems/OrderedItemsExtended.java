package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import java.util.Objects;

public class OrderedItemsExtended {

    private int id;
    private int partId;
    private String partName;
    private int qty;
    private int nestedQty;
    private int toNestQty;
    private String profile;
    private double profileLength;
    private String material;
    private String orderNumber = null;
    private String article = null;
    private String drawing = null;
    private double weight;

    public OrderedItemsExtended() {
    }

    public OrderedItemsExtended(OrderedItemsExtended orderedItem) {
        this.id = orderedItem.id;
        this.partId = orderedItem.partId;
        this.partName = orderedItem.partName;
        this.qty = orderedItem.qty;
        this.nestedQty = orderedItem.nestedQty;
        this.toNestQty = orderedItem.toNestQty;
        this.profile = orderedItem.profile;
        this.profileLength = orderedItem.profileLength;
        this.material = orderedItem.material;
        this.orderNumber = orderedItem.orderNumber;
        this.article = orderedItem.article;
        this.drawing = orderedItem.drawing;
        this.weight = orderedItem.weight;
    }


    public OrderedItemsExtended(Integer id, int id1, String partName, Integer qty, String profile, double profileLength, String material) {
        this.id = id;
        this.partId = id1;
        this.partName = partName;
        this.qty = qty;
        this.profile = profile;
        this.profileLength = profileLength;
        this.material = material;
    }

    public OrderedItemsExtended(Integer id, Integer partId, String partName, int qty, int toNestQty, int nestedQty, String profile, double profileLength, String material, String orderNumber, String article, String drawing, double weight) {
        this.id = id;
        this.partId = partId;
        this.partName = partName;
        this.qty = qty;
        this.nestedQty = nestedQty;
        this.toNestQty = toNestQty;
        this.profile = profile;
        this.profileLength = profileLength;
        this.material = material;
        this.orderNumber = orderNumber;
        this.article = article;
        this.drawing = drawing;
        this.weight = weight;
    }

    public int getNestedQty() {
        return nestedQty;
    }

    public void setNestedQty(int nestedQty) {
        this.nestedQty = nestedQty;
    }

    public int getToNestQty() {
        return toNestQty;
    }

    public void setToNestQty(int toNestQty) {
        this.toNestQty = toNestQty;
    }

    public void nestItem() {
        toNestQty--;
        nestedQty++;
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

    public void changeNestedQtyByValue(int nestedQty) {
        this.nestedQty -= nestedQty;
        toNestQty = qty - this.nestedQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItemsExtended that = (OrderedItemsExtended) o;
        return id == that.id && partId == that.partId && qty == that.qty && nestedQty == that.nestedQty && Double.compare(that.profileLength, profileLength) == 0 && Double.compare(that.weight, weight) == 0 && Objects.equals(partName, that.partName) && Objects.equals(profile, that.profile) && Objects.equals(material, that.material) && Objects.equals(orderNumber, that.orderNumber) && Objects.equals(article, that.article) && Objects.equals(drawing, that.drawing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partId, partName, qty, nestedQty, toNestQty, profile, profileLength, material, orderNumber, article, drawing, weight);
    }

    @Override
    public String toString() {
        return "OrderedItemsExtended{" + "id=" + id + ", partName='" + partName + '\'' + ", qty=" + qty + ", profile='" + profile + '\'' + ", profileLength=" + profileLength + ", material='" + material + '\'' + '}';
    }
}
