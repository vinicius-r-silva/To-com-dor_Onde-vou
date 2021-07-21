package com.example.tocomdor;

public class Coord{
	public float lat;
	public float lon;


	public Coord(float la, float lo){
		this.lat = la;
		this.lon = lo;
	}

	public Coord(double la, double lo) {
		this.lat = (float) la;
		this.lon = (float) lo;
	}

	public float calcDist(Coord pos){
		double lat1 = pos.lat;
		double lat2 = lat;
		double lon1 = pos.lon;
		double lon2 = lon;

		double p = 0.017453292519943295;    // Math.PI / 180
		double a = 0.5 - Math.cos((lat2 - lat1) * p)/2 +
				Math.cos(lat1 * p) * Math.cos(lat2 * p) *
							(1 - Math.cos((lon2 - lon1) * p))/2;
	
		return (float) (12742 * Math.asin(Math.sqrt(a))); // 2 * R; R = 6371 km
	}

	public float quickDist(Coord pos){
		float x = (float) ((pos.lon - lon) * Math.cos( 0.5*(lat + pos.lat)));
		float y = pos.lat - lat;

		return (float) (6371 * Math.sqrt( x*x + y*y ));
	}
}
