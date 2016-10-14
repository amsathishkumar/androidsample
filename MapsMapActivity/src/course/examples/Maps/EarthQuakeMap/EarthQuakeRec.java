package course.examples.Maps.EarthQuakeMap;

import com.google.android.maps.GeoPoint;

public class EarthQuakeRec {
private GeoPoint location;
private double magnitude;

protected EarthQuakeRec(int lat, int lng, double magnitude) {
	super();
	this.location = new GeoPoint(lat, lng);
	this.magnitude = magnitude;
}

public GeoPoint getGeoPoint() {
	return location;
}


public double getMagnitude() {
	return magnitude;
}

}
