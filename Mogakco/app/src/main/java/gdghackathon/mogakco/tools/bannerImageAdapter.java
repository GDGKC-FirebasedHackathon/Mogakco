package gdghackathon.mogakco.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class bannerImageAdapter extends RegisteredFragmentStatePagerAdapter {
    List<String> urls;

    public bannerImageAdapter(FragmentManager fm) {
        super(fm);
        urls = new ArrayList<>();

    }

    public void addBannerImage(String url) {
        urls.add(url);
    }

    @Override
    public int getCount() {
        if(urls != null) {
            return urls.size();
        }else{
            return 0;
        }
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("bannerImageAdapter", String.format("getItem(%d) called", position));
        if(position<getCount()) {
            return BannerImageFragment.newInstance(urls.get(position));
        }else{
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "urls";
    }
}
