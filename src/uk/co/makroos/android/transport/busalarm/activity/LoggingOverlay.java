package uk.co.makroos.android.transport.busalarm.activity;

import android.util.Log;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class LoggingOverlay extends Overlay {

	private final static String TAG = LoggingOverlay.class.getSimpleName();

	@Override
	public boolean onTouchEvent(MotionEvent e, MapView mapView) {
		Log.d(TAG, "Touching at X:" + e.getX() + ", Y:" + e.getY());

		GeoPoint p = mapView.getProjection().fromPixels((int) e.getX(), (int) e.getY());
		Log.d(TAG, "Latitude:, " + p.getLatitudeE6() + "Longitude:" + p.getLongitudeE6());

		// TODO Auto-generated method stub
		return super.onTouchEvent(e, mapView);
	}

}
