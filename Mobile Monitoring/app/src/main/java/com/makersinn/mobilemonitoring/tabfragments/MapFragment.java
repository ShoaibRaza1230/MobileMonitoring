package com.makersinn.mobilemonitoring.tabfragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.makersinn.mobilemonitoring.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FreedomRider on 2/21/2019.
 */

public class MapFragment extends Fragment {
    private static final int REQUEST_LACATION = 1;

    Button locationBtn;

    TextView locationTV;

    LocationManager locationManager;

    String latitude, longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map_position, container, false);

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.
                ACCESS_FINE_LOCATION}, REQUEST_LACATION);

        locationBtn = view.findViewById(R.id.locationBtn);

        locationTV = view.findViewById(R.id.locationTV);


        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            getLocation();

        }

        return view;
    }


    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=

                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),

                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission
                    .ACCESS_FINE_LOCATION}, REQUEST_LACATION);

        } else {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();

                double longi = location.getLongitude();

                latitude = String.valueOf(latti);

                longitude = String.valueOf(longi);

                locationTV.setText("Your Current Location is " + "\n" + "Latitude" + latitude + "\n" + "Longitude"
                        + longitude);

                sendLocStatus(latitude,longitude);
            } else {

               Toast.makeText(this.getActivity(),"Sorry Location can't trace",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());

        builder.setMessage("Please turn on your GPS Connection").setCancelable(false)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int id) {
                dialog.cancel();

            }
        });
    }

    private void sendLocStatus(final String lat, final String lang) {
        String url = "https://incurrable-twine.000webhostapp.com/Mobile/UpdateLocation.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity());  // this = context
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Updating Bettery please wait");
//        progressDialog.show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "" + response, Toast.LENGTH_LONG).show();
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
//                        Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("latitude", lat);
                params.put("longitude", lang);


                return params;
            }
        };
        queue.add(postRequest);

    }
}
