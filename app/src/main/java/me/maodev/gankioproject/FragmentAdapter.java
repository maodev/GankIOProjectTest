package me.maodev.gankioproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.maodev.gankioproject.tool.LazyFragmentPagerAdapter;

/**
 * Created by maoyu on 16/12/2.
 */

public class FragmentAdapter extends LazyFragmentPagerAdapter {
    ArrayList<String> mtabTitle;
     ArrayList<Fragment> mtabFragment;

    public FragmentAdapter(FragmentManager fm, ArrayList<String> tabTitle, ArrayList<Fragment> tabFragment) {
        super(fm);
        this.mtabTitle = tabTitle;
        this. mtabFragment= tabFragment;
    }
//    @Override
//    public Fragment getItem(int position) {
//        return mtabFragment.get(position);
//    }

    @Override
    protected Fragment getItem(ViewGroup container, int position) {
        return mtabFragment.get(position);
    }

    @Override
    public int getCount() {
        return mtabFragment == null ? 0 : mtabFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtabTitle.get(position);
    }
}