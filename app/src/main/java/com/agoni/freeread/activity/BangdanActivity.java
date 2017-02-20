package com.agoni.freeread.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.agoni.freeread.R;
import com.agoni.freeread.fragment.NanbangFragment;
import com.agoni.freeread.fragment.NvbangFragment;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BangdanActivity extends SwipeBackActivity {

    String[] nanbangid =new String[]{"54d42d92321052167dfb75e3","564547c694f1c6a144ec979b",
            "564eb878efe5b8e745508fde","54d42e72d9de23382e6877fb","564d8003aca44f4f61850fcd",
            "564d80457408cfcd63ae2dd0","54d430e9a8cb149d07282496"};
    String[] nvbangid=new String[]{"54d43437d47d13ff21cad58b","5645482405b052fe70aeb1b5",
            "564eb8a9cf77e9b25056162d","54d43709fd6ec9ae04184aa5","564d80d0e8c613016446c5aa",
            "564d81151109835664770ad7","550b841715db45cd4b022107"};

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdan);
        findView();
        iniviewPager();
    }

    private void findView() {
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
    }

    private void iniviewPager() {
        final List<Fragment> fragments=new ArrayList<>();
        int num = getIntent().getIntExtra("num", 0);
        NanbangFragment nanbangFragment = new NanbangFragment();
        nanbangFragment.setData(nanbangid[num]);
        NvbangFragment nvbangFragment = new NvbangFragment();
        nvbangFragment.setData(nvbangid[num]);
        fragments.add(nanbangFragment);
        fragments.add(nvbangFragment);

        final String[] titles=new String[]{"男生","女生"};

        mViewPager.setOffscreenPageLimit(2);

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
