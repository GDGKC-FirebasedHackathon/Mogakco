package gdghackathon.mogakco.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.core.AppController;
import gdghackathon.mogakco.tools.MaterialDialog;
import gdghackathon.mogakco.tools.mapsearch.Item;
import gdghackathon.mogakco.tools.mapsearch.OnFinishSearchListener;
import gdghackathon.mogakco.tools.mapsearch.Searcher;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class MapDialog extends MaterialDialog implements View.OnClickListener,MapView.POIItemEventListener {
    @Bind(R.id.map)
    RelativeLayout mapContainer;
    @Bind(R.id.edtxSearch)
    EditText edtxSearch;
    @Bind(R.id.btnSearch)
    Button btnSearch;
    Context mContext;
    MapView mapView;
    protected InputMethodManager ipm;
    private HashMap<Integer, Item> mTagItemMap = new HashMap<Integer, Item>();


    public MapDialog(Context context) {
        super(context);
        mContext = context;
    }

    public static MapDialog init(Context context) {
        MapDialog dialog = new MapDialog(context);
        dialog.setup(context);
        return dialog;
    }

    private void setup(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_map, null);
        ButterKnife.bind(this, view);
        ipm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);


        mapView = new MapView(context);
        mapView.setDaumMapApiKey(AppController.getInstance().getLocalStore().getDaumMapAPIkey());

        mapContainer.addView(mapView);
        setCanceledOnTouchOutside(true);

        btnSearch.setOnClickListener(this);
        setView(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                String query = edtxSearch.getText().toString();
                if (query == null || query.length() == 0) {
                    Toast.makeText(mContext, "장소를 입력하세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                ipm.hideSoftInputFromWindow(edtxSearch.getWindowToken(), 0);

                MapPoint.GeoCoordinate geoCoordinate = mapView.getMapCenterPoint().getMapPointGeoCoord();
                double latitude = geoCoordinate.latitude; // 위도
                double longitude = geoCoordinate.longitude; // 경도
                int radius = 10000; // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 사용. meter 단위 (0 ~ 10000)
                int page = 1; // 페이지 번호 (1 ~ 3). 한페이지에 15개

                Searcher searcher = new Searcher(); // net.daum.android.map.openapi.search.Searcher
                searcher.searchKeyword(mContext, query, latitude, longitude, radius, page, AppController.getInstance().getLocalStore().getDaumMapAPIkey(), new OnFinishSearchListener() {
                    @Override
                    public void onSuccess(List<Item> itemList) {
                        mapView.removeAllPOIItems(); // 기존 검색 결과 삭제
                        MapPointBounds mapPointBounds = new MapPointBounds();

                        for (int i = 0; i < itemList.size(); i++) {
                            MapPOIItem poiItem = new MapPOIItem();
                            poiItem.setItemName(itemList.get(i).title);
                            poiItem.setTag(i);
                            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(itemList.get(i).latitude, itemList.get(i).longitude);
                            poiItem.setMapPoint(mapPoint);
                            mapPointBounds.add(mapPoint);
                            poiItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                            poiItem.setCustomImageResourceId(R.drawable.ic_marker);
                            poiItem.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
                            poiItem.setCustomImageAutoscale(false);
                            poiItem.setCustomSelectedImageResourceId(R.drawable.ic_marker_selected);
                            poiItem.setCustomImageAnchor(0.5f, 1.0f);

                            mapView.addPOIItem(poiItem);
                            mapView.fitMapViewAreaToShowAllPOIItems();
                            mTagItemMap.put(poiItem.getTag(), itemList.get(i));
                        }
                        setListener();
                    }

                    @Override
                    public void onFail() {
                    }
                });

                break;
            case R.id.edtxSearch:
                break;
        }
    }
    private void setListener(){
        mapView.setPOIItemEventListener(this);
    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        Toast.makeText(mContext,mapPOIItem.getItemName()+"geoCoord = "+mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude+","+mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
