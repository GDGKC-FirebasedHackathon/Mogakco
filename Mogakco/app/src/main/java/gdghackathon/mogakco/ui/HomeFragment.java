package gdghackathon.mogakco.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.model.MogakcoEvent;
import gdghackathon.mogakco.tools.bannerImageAdapter;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    @Bind(R.id.recvEvent)
    RecyclerView recvEvent;
    @Bind(R.id.btnMogakco)
    LinearLayout btnMogakco;

    bannerImageAdapter topBannerAdapter;
    EventAdapter mAdapter;

    Timer swipeTimer;
    Handler handler;
    Runnable update;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        initializeLayout(v);
        return v;
    }

    private void initializeLayout(View v) {

        List<MogakcoEvent> mogakcoEvents = new ArrayList<>();
        mogakcoEvents.add(new MogakcoEvent("모각코", "http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png"));
        mogakcoEvents.add(new MogakcoEvent("모각코2", "http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png"));
        mogakcoEvents.add(new MogakcoEvent("모각코3", "http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png"));
        mogakcoEvents.add(new MogakcoEvent("모각코4", "http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png"));
        mogakcoEvents.add(new MogakcoEvent("모각코5", "http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png"));

        mAdapter = new EventAdapter(getActivity(), null, new EventAdapter.OnClickListener() {
            @Override
            public void OnClick(MogakcoEvent event) {
                startActivity(EventDetailActivity.getStartIntent(getActivity(), event));
            }
        });
        recvEvent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recvEvent.setAdapter(mAdapter);

        btnMogakco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapDialog.init(getActivity()).show();
            }
        });
        mAdapter.add(mogakcoEvents);


        topBannerAdapter = new bannerImageAdapter(getFragmentManager());

        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_1.png");
        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_2.png");
        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_3.png");
        topBannerAdapter.addBannerImage("http://storage.googleapis.com/mathpresso-storage/uploads/banners/16_giftpageimage_4.png");

        topBannerAdapter.notifyDataSetChanged();

        mPager.setAdapter(topBannerAdapter);
        mPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapDialog.init(getActivity()).show();
            }
        });

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
