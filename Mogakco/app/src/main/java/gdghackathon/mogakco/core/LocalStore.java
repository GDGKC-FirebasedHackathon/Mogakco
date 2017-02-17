package gdghackathon.mogakco.core;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStore {

    public static final String SP_NAME = "LocalData";
    private SharedPreferences sharedPref;

    public String getDaumMapAPIkey() {
        return sharedPref.getString("daum_map_apikey", "4b781301b49fff6c43a6a43a1acff03c");
    }


    public LocalStore(Context context) {

        sharedPref = context.getSharedPreferences(SP_NAME, 0);
    }

}
