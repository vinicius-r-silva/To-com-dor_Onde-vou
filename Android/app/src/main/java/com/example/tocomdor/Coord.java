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
		//haversine formula
		float dlon = pos.lon - lon;
		float dlat = pos.lat - lat;
		float a = (float) (Math.pow(Math.sin(dlat/2),2) + Math.cos(lat) * Math.cos(pos.lat) * Math.pow(Math.sin(dlon/2),2));

		//Radius of earth in kilometers is 6371
		return (float) (6371* (2 * Math.asin(Math.sqrt(a))));
	}

	public float quickDist(Coord pos){
		float x = (float) ((pos.lon - lon) * Math.cos( 0.5*(lat + pos.lat)));
		float y = pos.lat - lat;

		return (float) (6371 * Math.sqrt( x*x + y*y ));
	}
}
