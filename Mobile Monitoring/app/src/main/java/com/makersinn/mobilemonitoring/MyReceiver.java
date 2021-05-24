package com.makersinn.mobilemonitoring;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVER="android.provider.Telephony.SMS_RECEIVER";

    private static final String TAG="SmsBroadcastReceiver";

    String msg,phoneNo="";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG,"Intend Recieved"+intent.getAction());

        if(intent.getAction()==SMS_RECEIVER)
        {
            Bundle dataBundle=intent.getExtras();

            if (dataBundle!=null)
            {
                Object[] mypdu=(Object[])dataBundle.get("mypdu");

                final SmsMessage[] messages=new SmsMessage[mypdu.length];

                for (int i=0;i<mypdu.length;i++)
                {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)

                    {
                        String format=dataBundle.getString("format");

                        messages[i]=SmsMessage.createFromPdu((byte[])mypdu[i],format);

                    }
                    else {
                        messages[i]=SmsMessage.createFromPdu((byte[])mypdu[i]);

                    }
                    msg=messages[i].getMessageBody();

                    phoneNo=messages[i].getOriginatingAddress();
                }
            }
        }
    }
}
