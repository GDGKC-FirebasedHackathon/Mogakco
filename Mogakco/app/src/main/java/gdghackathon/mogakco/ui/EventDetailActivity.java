package gdghackathon.mogakco.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.core.AppController;
import gdghackathon.mogakco.model.MogakcoEvent;

/**
 * Created by choijinjoo on 2017. 2. 17..
 */

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mapContainer)
    RelativeLayout mapContainer;
    @Bind(R.id.txtvTitle)
    TextView txtvTitle;
    @Bind(R.id.txtvDate)
    TextView txtvDate;
    @Bind(R.id.txtvDescription)
    TextView txtvDescription;
    @Bind(R.id.btnMapDetail)
    LinearLayout btnMapDetail;

    @Bind(R.id.imgvEvent)
    SimpleDraweeView imgvEvent;
    MapView mapView;
    MogakcoEvent mEvent;

    public static Intent getStartIntent(Context context, MogakcoEvent mogakcoEvent) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra("event", mogakcoEvent);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetail);
        ButterKnife.bind(this);
        mEvent = (MogakcoEvent) getIntent().getSerializableExtra("event");
        initializeLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mEvent.getLatitude() != null && mEvent.getLongitude() != null) {
            drawMap();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapContainer.removeView(mapView);
    }

    private void initializeLayout() {
        txtvTitle.setText(mEvent.getTitle());
        txtvDescription.setText(mEvent.getDescription());

        btnMapDetail.setOnClickListener(this);

        if (mEvent.getImage() != null) {
            imgvEvent.setImageURI(mEvent.getImage());
        }

    }

    // variables for test
    double lat;
    double lon;

    private void drawMap() {
        mapView = new MapView(this);
        mapView.setDaumMapApiKey(AppController.getInstance().getLocalStore().getDaumMapAPIkey());
        mapContainer.addView(mapView);

        MapPOIItem poiItem = new MapPOIItem();

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(mapView.getMapCenterPoint().getMapPointGeoCoord().latitude, mapView.getMapCenterPoint().getMapPointGeoCoord().longitude);
        lat = mapView.getMapCenterPoint().getMapPointGeoCoord().latitude;
        lon = mapView.getMapCenterPoint().getMapPointGeoCoord().longitude;

//            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(mEvent.getLatitude(), mEvent.getLongitude());
        poiItem.setMapPoint(mapPoint);

        MapPointBounds mapPointBounds = new MapPointBounds();
        mapPointBounds.add(mapPoint);
        poiItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        poiItem.setCustomImageResourceId(R.drawable.ic_marker);
        poiItem.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
        poiItem.setCustomImageAutoscale(false);
        poiItem.setCustomSelectedImageResourceId(R.drawable.ic_marker_selected);
        poiItem.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(poiItem);
        mapView.fitMapViewAreaToShowAllPOIItems();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMapDetail:
                startActivity(MapDetailActivity.getStartIntent(EventDetailActivity.this, mEvent));
                break;
        }
    }

}
