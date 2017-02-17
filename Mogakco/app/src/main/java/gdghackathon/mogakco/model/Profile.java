package gdghackathon.mogakco.model;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mk on 2017-02-17.
 */

public class Profile {

    public String firebaseUid;
    public String email;
    public String name;
    public String profileImgUrl;

    public Profile(){}

    public Profile(String firebaseUid, String email, String name, String profileImgUrl){
        this.firebaseUid = firebaseUid;
        this.email = email;
        this.name = name;
        this.profileImgUrl = profileImgUrl;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("firebaseUid", firebaseUid);
        result.put("email", email);
        result.put("name", name);
        result.put("profileImgUrl", profileImgUrl);
        return result;
    }

    public static Profile parseSnapshot(DataSnapshot snapshot){
        Profile profile = new Profile();
        profile.firebaseUid = (String) snapshot.child("firebaseUid").getValue();
        profile.email = (String) snapshot.child("email").getValue();
        profile.name = (String) snapshot.child("name").getValue();
        profile.profileImgUrl = (String) snapshot.child("profileImgUrl").getValue();

        return profile;
    }
}
