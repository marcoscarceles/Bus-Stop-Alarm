package uk.co.makroos.android.transport.busalarm.domain;

import android.util.Log;

public class Stop {

	private static final String TAG = Stop.class.getSimpleName();

	String name;
	double latitude;
	double longitude;
	double height;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getCoordinates() {
		return new StringBuilder().append(longitude).append(",").append(latitude).append(",").append(height).toString();
	}

	public void setCoordinates(String coordinatesStr) {
		try {
			String[] coordinates = coordinatesStr.split(",");
			latitude = Double.parseDouble(coordinates[0]);
			longitude = Double.parseDouble(coordinates[1]);
			height = Double.parseDouble(coordinates[2]);
		} catch (Exception e) {
			Log.i(TAG, "Could not process coordinates " + coordinatesStr, e);
		}
	}

	@Override
	public String toString() {
		return "Bus Stop '" + name + "', located in " + latitude + "," + longitude;
	}

}
