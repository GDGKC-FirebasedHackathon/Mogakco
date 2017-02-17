package gdghackathon.mogakco.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

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

public class MapDetailActivity extends AppCompatActivity {
    @Bind(R.id.containerMap)
    RelativeLayout containerMap;
    MogakcoEvent mogakcoEvent;
    MapView mapView;

    public static Intent getStartIntent(Context context, MogakcoEvent mogakcoEvent) {
        Intent intent = new Intent(context, MapDetailActivity.class);
        intent.putExtra("mogakcoEvent", mogakcoEvent);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdetail);
        ButterKnife.bind(this);
        mogakcoEvent = (MogakcoEvent) getIntent().getExtras().get("mogakcoEvent");

    }

    @Override
    protected void onResume() {
        super.onResume();
        drawMap(mogakcoEvent.getLatitude(), mogakcoEvent.getLongitude());
    }


    @Override
    protected void onPause() {
        super.onPause();
        containerMap.removeView(mapView);
    }

    private void drawMap(double lat, double lon) {
        mapView = new MapView(this);
        mapView.setDaumMapApiKey(AppController.getInstance().getLocalStore().getDaumMapAPIkey());
        containerMap.addView(mapView);

        MapPOIItem poiItem = new MapPOIItem();

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon);

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


}
