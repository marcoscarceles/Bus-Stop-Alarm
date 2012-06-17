package uk.co.makroos.android.transport.busalarm.domain;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;

public class BusStop extends Stop {

	int easting;
	int northing;

	public int getEasting() {
		return easting;
	}

	public void setEasting(int easting) {
		this.easting = easting;
	}

	public int getNorthing() {
		return northing;
	}

	public void setNorthing(int northing) {
		this.northing = northing;
	}

	public void recalculateWGS84() {
		LatLng latLng = new OSRef(easting, northing).toLatLng();
		latLng.toWGS84();
		setLatitude(latLng.getLat());
		setLongitude(latLng.getLng());
	}

	public void recalculateOSGB() {
		LatLng latLng = new LatLng(getLatitude(), getLongitude());
		latLng.toOSGB36();
		OSRef osRef = latLng.toOSRef();
		easting = (int) osRef.getEasting();
		northing = (int) osRef.getNorthing();
	}
}
