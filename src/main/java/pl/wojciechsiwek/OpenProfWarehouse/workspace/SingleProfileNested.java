package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import java.util.Map;

public class SingleProfileNested {
    private String profileSignature;
    private Map itemsOnProfile; //key is orderedItemId, value is qty on profile

    SingleProfileNested(String profileSignature, Map itemsOnProfile) {
        this.profileSignature = profileSignature;
        this.itemsOnProfile = itemsOnProfile;
    }

    String getProfileSignature() {
        return profileSignature;
    }

    Map getItemsOnProfile() {
        return itemsOnProfile;
    }
}
