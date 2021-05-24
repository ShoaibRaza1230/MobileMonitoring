package com.makersinn.mobilemonitoring;

import android.Manifest;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.R

import com.makersinn.mobilemonitoring.adapters.FragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {
    ViewPager viewpager;
    TabLayout tabLayout;
    private int tabIcons[] = {R.drawable.missed_call, R.drawable.sms_icon,
            R.drawable.bettery_icon,
            R.drawable.location_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        
        //setting viewpager and tablayout
        viewpager = findViewById(R.id.viewpager);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(),
                getApplicationContext());

        viewpager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabView);
        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewpager);

        for (int i = 0; i < tabIcons.length; i++) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }
}
