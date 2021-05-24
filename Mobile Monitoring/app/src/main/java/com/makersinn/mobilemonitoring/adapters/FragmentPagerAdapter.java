package com.makersinn.mobilemonitoring.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.makersinn.mobilemonitoring.tabfragments.CallLogFragment;
import com.makersinn.mobilemonitoring.tabfragments.BetteryFragment;
import com.makersinn.mobilemonitoring.tabfragments.MapFragment;
import com.makersinn.mobilemonitoring.tabfragments.SmsLogFragment;

/**
 * Created by FreedomRider on 2/21/2019.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Missed Calls", "Sms", "Battery Status","Location"};
    //    private int tabTitles[]={R.drawable.call_log,R.drawable.call_log,R.drawable.call_log,R.drawable.call_log};
    private Context context;
    FragmentManager fm;

    public FragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CallLogFragment callLogFragment = new CallLogFragment();
                return callLogFragment;
            case 1:
                SmsLogFragment smsLogFragment = new SmsLogFragment();
                return smsLogFragment;
            case 2:
                BetteryFragment betteryFragment = new BetteryFragment();
                return betteryFragment;
            case 3:
                MapFragment mapFragment=new MapFragment();
                return mapFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//
//    }
}
