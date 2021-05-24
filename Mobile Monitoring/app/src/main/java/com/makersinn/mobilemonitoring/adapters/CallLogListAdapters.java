package com.makersinn.mobilemonitoring.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makersinn.mobilemonitoring.R;
import com.makersinn.mobilemonitoring.modal.ContactModal;

import java.util.ArrayList;

/**
 * Created by FreedomRider on 4/15/2019.
 */

public class CallLogListAdapters extends BaseAdapter {
    Context context;
    ArrayList<ContactModal> contactLog;
    LayoutInflater inflater;

    public CallLogListAdapters(Context context, ArrayList<ContactModal> contactLog) {
        this.context = context;
        this.contactLog = contactLog;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contactLog.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_item, null);
        ImageView loggIcon = convertView.findViewById(R.id.call_log_icon);
        TextView number = convertView.findViewById(R.id.mobileNumber);
        TextView name = convertView.findViewById(R.id.name);

        loggIcon.setImageResource(R.drawable.missed_call);
        number.setText(contactLog.get(position).getContactNumber());
        name.setText(contactLog.get(position).getContactName());

        return convertView;
    }
}
