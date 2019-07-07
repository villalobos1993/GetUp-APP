package com.example.castriwolf.getup2.Clases;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.castriwolf.getup2.Activitys.MapsActivity.mMap;
import static com.example.castriwolf.getup2.Activitys.MapsActivity.pintado;

/**
 * TaskParser
 * funciona para pintar las polinias de una ruta
 */
public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>> {

    public TaskParser(){}


    @Override
    protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
        JSONObject jsonObject = null;
        List<List<HashMap<String, String>>> routes = null;
        try {
            jsonObject = new JSONObject(strings[0]);
            DirectionsParser directionsParser = new DirectionsParser();
            routes = directionsParser.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routes;
    }

    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
        //Get list route and display it into the map
        ArrayList points = null;
        PolylineOptions polylineOptions = null;


        if (pintado == true) {
            mMap.clear();
            pintado = false;
        }

        for (List<HashMap<String, String>> path : lists) {
            points = new ArrayList();
            polylineOptions = new PolylineOptions();


            for (HashMap<String, String> point : path) {
                double lat = Double.parseDouble(point.get("lat"));
                double lon = Double.parseDouble(point.get("lon"));

                points.add(new LatLng(lat, lon));
            }

            polylineOptions.addAll(points);
            polylineOptions.width(15);
            polylineOptions.color(Color.BLUE);
            polylineOptions.geodesic(true);
        }

        if (polylineOptions != null) {
            mMap.addPolyline(polylineOptions);
            points.clear();
            polylineOptions.getPoints().clear();
            pintado = true;
        }
        //else {Toast.makeText(getApplicationContext(), "Direcciones no encontradas!", Toast.LENGTH_SHORT).show();}

    }


}
