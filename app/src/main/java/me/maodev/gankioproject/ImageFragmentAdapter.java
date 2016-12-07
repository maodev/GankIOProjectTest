package me.maodev.gankioproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by maoyu on 16/12/7.
 */

public class ImageFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mList;

    public ImageFragmentAdapter(FragmentManager fm, List<String> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
       ImageItemFragment fragment = ImageItemFragment.newInstance(mList.get(position));

        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
