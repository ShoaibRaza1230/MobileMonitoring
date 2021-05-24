package com.makersinn.mobilemonitoring.tabfragments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.R

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

public class BetteryFragment extends Fragment {
    int value;

    TextView batteryTV;

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);

            value=level;

            batteryTV.setText(String.valueOf(level + "%"));

            sendBatteryStatus(String.valueOf(value));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_bettery, container, false);

        batteryTV=view.findViewById(R.id.batteryTV);

        getActivity().getApplicationContext().registerReceiver(this.broadcastReceiver,new

                IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        return view;
    }

    private void sendBatteryStatus(final String bettryStatus)
    {
        String url="https://incurrable-twine.000webhostapp.com/Mobile/UpdateBettery.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity());  // this = context
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
//        progressDialog.setMessage("Updating Bettery please wait");
//        progressDialog.show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
//                        Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("bettery_status", bettryStatus);


                return params;
            }
        };
        queue.add(postRequest);

    }

}
