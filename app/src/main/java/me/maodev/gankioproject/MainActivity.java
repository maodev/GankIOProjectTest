package me.maodev.gankioproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import me.maodev.gankioproject.tool.LazyViewPager;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout  tabLayout;
    LazyViewPager vp;
    ArrayList<String> tabTitle = new ArrayList<String>();
    ArrayList<Fragment> tabFragment = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        vp = (LazyViewPager) findViewById(R.id.vp);
        tabTitle.add(getString(R.string.tabtitle_android));
        tabTitle.add(getString(R.string.tabtitle_ios));
        tabTitle.add(getString(R.string.tabtitle_ui));

        tabFragment.add(TypeFragment.newInstance("android"));
        tabFragment.add(TypeFragment.newInstance("ios"));
        tabFragment.add(TypeFragment.newInstance("ui"));

        FragmentAdapter fragmentadapter = new FragmentAdapter(getSupportFragmentManager(),tabTitle,tabFragment);
        vp.setAdapter(fragmentadapter);
         vp.setOffscreenPageLimit(3);
//        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                switch (position){
//                    case 0:
//                         return  TypeFragment.newInstance("android");
//
//                    case 1:
//                        return  TypeFragment.newInstance("ios");
//
//                    case 2:
//                        return  TypeFragment.newInstance("ui");
//
//                }
//                return null;
//            }
//
//            @Override
//            public int getCount() {
//                return 3;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                switch (position){
//                    case 0:
//                        return  "Android";
//
//                    case 1:
//                        return  "iOS";
//
//                    case 2:
//                        return "前端";
//                }
//                return "";
//            }
//
//        });
        tabLayout.setupWithViewPager(vp);
    }
}
