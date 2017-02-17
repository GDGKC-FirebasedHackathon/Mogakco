package gdghackathon.mogakco.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.tools.bannerImageAdapter;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;

    bannerImageAdapter topBannerAdapter;
    Timer swipeTimer;
    Handler handler;
    Runnable update;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        initializeLayout();
        return v;
    }

    private void initializeLayout() {

        topBannerAdapter = new bannerImageAdapter(getFragmentManager());
        mPager.setAdapter(topBannerAdapter);

        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png");
        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_2.png");
        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_3.png");
        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_4.png");

        topBannerAdapter.notifyDataSetChanged();
        final Resources resources = this.getResources();
        final float density = resources.getDisplayMetrics().density;

        indicator.setRadius(3 * density);
        indicator.setGap(3 * density);
        indicator.setPageColor(resources.getColor(R.color.black));
        indicator.setFillColor(resources.getColor(R.color.white));
        indicator.setStrokeWidth(0);
        indicator.setViewPager(mPager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                restartSwipeTimer();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (handler == null) {
            handler = new Handler();
        }
        if (update == null) {
            update = new Runnable() {
                public void run() {
                    if (mPager.getCurrentItem() == topBannerAdapter.getCount() - 1) {
                        mPager.setCurrentItem(0);
                    } else {
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
                    }
                }
            };
        }

        restartSwipeTimer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    private void restartSwipeTimer() {
        if (swipeTimer != null) {
            swipeTimer.cancel();
        }
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 5000, 5000);
    }
}
