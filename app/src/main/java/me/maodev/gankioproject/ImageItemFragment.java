package me.maodev.gankioproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by maoyu on 16/12/7.
 */

public class ImageItemFragment extends Fragment{

    private String url;
    public static ImageItemFragment newInstance(String url) {
        ImageItemFragment fragment = new ImageItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            url = getArguments().getString("url");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_image,container,false);
        // ButterKnife.bind(this,view);
        ImageView iv = (ImageView)view.findViewById(R.id.iv);
        GlideImageLoader.LoadImage(iv,url);
        return view;
    }
}
