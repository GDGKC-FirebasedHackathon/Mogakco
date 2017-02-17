package gdghackathon.mogakco.core;

import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class AppController extends MultiDexApplication {
    private static AppController mInstance;
    private LocalStore mLocalStore;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);

    }
    public LocalStore getLocalStore() {
        if (mLocalStore == null) {
            mLocalStore = new LocalStore(getApplicationContext());
        }

        return mLocalStore;
    }


    public static synchronized AppController getInstance() {
        return mInstance;
    }

}
