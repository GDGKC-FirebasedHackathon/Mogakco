package gdghackathon.mogakco.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;

public class BannerImageFragment extends Fragment  {

    @Bind(R.id.imgvContent)
    SimpleDraweeView imgvContent;
    String imageUrl;

    public static final BannerImageFragment newInstance(String url) {
        BannerImageFragment fragment = new BannerImageFragment();
        fragment.imageUrl = url;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_banner, container, false);
        ButterKnife.bind(this, v);

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        if (imageUrl != null) {
            imgvContent.setImageURI(imageUrl);
        }
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}

