package uk.co.makroos.android.transport.busalarm.activity;

import java.util.ArrayList;
import java.util.List;

import uk.co.makroos.android.transport.busalarm.domain.BusStop;
import uk.co.makroos.android.transport.busalarm.service.BusStopService;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class StopsOverlay extends ItemizedOverlay {

	private static final String TAG = StopsOverlay.class.getSimpleName();

	public StopsOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	private final ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

	public void init() {
		// TODO: Change to R.<>
		List<BusStop> stops = BusStopService.getInstance().getStopsFromLocalAsset("assets/bus_stops_example.csv");

		for (BusStop stop : stops) {
			Log.d(TAG, "Adding " + stop);
			GeoPoint point = new GeoPoint((int) (stop.getLatitude() * 1e6), (int) (stop.getLongitude() * 1e6));
			OverlayItem overlayitem = new OverlayItem(point, "", "");
			mOverlays.add(overlayitem);
			populate();
			Log.d(TAG, "Added item to overlay");
		}
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

}
