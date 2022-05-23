package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;
import java.util.Map;

public class SingleProfileNested {
    private String profileSignature;
    private List<OrderedItemsExtended> itemsOnProfile;

    SingleProfileNested(String profileSignature, List itemsOnProfile) {
        this.profileSignature = profileSignature;
        this.itemsOnProfile = itemsOnProfile;
    }

    public String getProfileSignature() {
        return profileSignature;
    }

    public void setProfileSignature(String profileSignature) {
        this.profileSignature = profileSignature;
    }

    public List getItemsOnProfile() {
        return itemsOnProfile;
    }

    public void setItemsOnProfile(List itemsOnProfile) {
        this.itemsOnProfile = itemsOnProfile;
    }
}
