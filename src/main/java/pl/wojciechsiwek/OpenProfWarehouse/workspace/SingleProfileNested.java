package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import java.util.Map;

public class SingleProfileNested {
    private String profileSignature;
    private Map itemsOnProfile; //key is orderedItemId, value is qty on profile

    SingleProfileNested(String profileSignature, Map itemsOnProfile) {
        this.profileSignature = profileSignature;
        this.itemsOnProfile = itemsOnProfile;
    }

    public String getProfileSignature() {
        return profileSignature;
    }

    public void setProfileSignature(String profileSignature) {
        this.profileSignature = profileSignature;
    }

    public Map getItemsOnProfile() {
        return itemsOnProfile;
    }

    public void setItemsOnProfile(Map itemsOnProfile) {
        this.itemsOnProfile = itemsOnProfile;
    }
}
