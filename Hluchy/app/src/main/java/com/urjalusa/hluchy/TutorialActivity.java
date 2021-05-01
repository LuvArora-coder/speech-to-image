package com.urjalusa.hluchy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ViewPager screenPager = findViewById(R.id.screen_viewPager);
        TabLayout tabIndicator = findViewById(R.id.tab_indicator);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1F00AB"));
        actionBar.setBackgroundDrawable(colorDrawable);

        List<ScreenFragment> screenFragmentList = new ArrayList<>();
        screenFragmentList.add(new ScreenFragment(R.drawable.tut1));
        screenFragmentList.add(new ScreenFragment(R.drawable.tut2));
        screenFragmentList.add(new ScreenFragment(R.drawable.tut3));
        screenFragmentList.add(new ScreenFragment(R.drawable.tut4));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, screenFragmentList);
        screenPager.setAdapter(viewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);
    }
}