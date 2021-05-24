package com.makersinn.mobilemonitoring.tabfragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.makersinn.mobilemonitoring.R;

/**
 * Created by FreedomRider on 2/21/2019.
 */

            public class SmsLogFragment extends Fragment {
           TextView smsTV,phoneTV;
           private static final  int permision=0;

        @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sms_log, container, false);
        smsTV=view.findViewById(R.id.smsTV);

        phoneTV=view.findViewById(R.id.phoneTV);

        smsTV.setText(Utility.message);

        phoneTV.setText(Utility.phoneNoMsg);


        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest
                    .permission.READ_SMS))
            {


            }
            else {
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.
                        READ_SMS},permision);
            }
        }

       return view;
    }


    @Override
              public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
                      switch (requestCode)
                  {
                   case permision:
                  {
                      if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                   {
                    if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED)

                    {
                        Toast.makeText(getActivity(),"Thanks for permitting",Toast.LENGTH_LONG).show();
                    }}
                else {
                    Toast.makeText(getActivity(),"Thanks for don't permitting",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}
