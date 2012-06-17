package uk.co.makroos.android.transport.busalarm.activity;

import java.util.List;

import uk.co.makroos.android.transport.busalarm.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class BusStopAlarmActivity extends MapActivity {

	private static final String TAG = BusStopAlarmActivity.class.getSimpleName();

	LinearLayout linearLayout;
	MapView mapView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Building map
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		List<Overlay> listOfOverlays = mapView.getOverlays();
		// listOfOverlays.add(new LoggingOverlay());
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		StopsOverlay stopsOverlay = new StopsOverlay(drawable);
		stopsOverlay.init();
		listOfOverlays.add(stopsOverlay);

		Log.d(TAG, "Created Map Activity");
	}

	public void init() {

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}