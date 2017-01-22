package com.agoni.freeread.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.agoni.freeread.R;
import com.agoni.freeread.fragment.BangdanFragment;
import com.agoni.freeread.fragment.BookShelfFragment;
import com.agoni.freeread.fragment.RecommentFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        findView();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void findView() {
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        iniviewPager();
    }

    private void iniviewPager() {
        final List<Fragment> fragments=new ArrayList<>();
        fragments.add(new RecommentFragment());
        fragments.add(new BangdanFragment());
        fragments.add(new BookShelfFragment());

        final String[] titles=new String[]{"推荐","榜单","书架"};

        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        TabLayout tablayout = (TabLayout) findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

    }

}
