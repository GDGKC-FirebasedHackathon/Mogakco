package gdghackathon.mogakco.Controller;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import gdghackathon.mogakco.model.Profile;

/**
 * Created by mk on 2017-02-17.
 */

public class ProfileController {
    private static final String TAG = ProfileController.class.getSimpleName();

    public static void createProfile(String firebaseUid, String email, String name, String profileImgUrl){
        Profile profile = new Profile(firebaseUid, email, name, profileImgUrl);
        Map<String, Object> profileValues = profile.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/profiles/" + firebaseUid, profileValues);
        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);

    }
}
