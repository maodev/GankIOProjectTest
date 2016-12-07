package me.maodev.gankioproject;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by maoyu on 16/12/5.
 */

public class MultipleAdapter extends  RecyclerView.Adapter<ViewHolder>  implements View.OnClickListener{
    private Context mcontext;
    private FragmentActivity mactivity;
    private ArrayList<ResultModel> mlist;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private final int TYPE_TEXT = 0;
    private final int TYPE_SINGLE_IMAGE = 1;
    private final int TYPE_PURE_TEXT = 2;
    public MultipleAdapter(FragmentActivity activity,Context context, ArrayList<ResultModel> list) {
        mcontext = context;
        mlist = list;
        mactivity =activity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType){
            case TYPE_TEXT:
                View view_text = LayoutInflater.from(mcontext).inflate(R.layout.adapter_item_text, parent,false);
                TextHolder holder = new TextHolder(view_text);
                view_text.setOnClickListener(this);
                return holder;
            case TYPE_SINGLE_IMAGE:
                View single_image_view = LayoutInflater.from(mcontext).inflate(R.layout.adapter_item_single_image, parent,false);
                SinglwImageViewHolder holder_single_holder = new SinglwImageViewHolder(single_image_view);
                single_image_view.setOnClickListener(this);
                return holder_single_holder;
            case TYPE_PURE_TEXT:
                View pure_text_view = LayoutInflater.from(mcontext).inflate(R.layout.adapter_item_pure_text, parent,false);
                PureTextHolder pure_text_holder = new PureTextHolder(pure_text_view);
                pure_text_view.setOnClickListener(this);
                return pure_text_holder;
               }
            return  null;

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof  TextHolder){
            ((TextHolder) holder).tv_date.setText(mlist.get(position).getPublishedAt().substring(0,10));
            ((TextHolder) holder).tv_name.setText(mlist.get(position).getWho());
            ((TextHolder) holder).tv_title.setText(mlist.get(position).getDesc());
            holder.itemView.setTag(mlist.get(position).getUrl());
        }else if(holder instanceof  SinglwImageViewHolder){
            ((SinglwImageViewHolder) holder).tv_date.setText(mlist.get(position).getPublishedAt().substring(0,10));
            ((SinglwImageViewHolder) holder).tv_name.setText(mlist.get(position).getWho());
            ((SinglwImageViewHolder) holder).tv_title.setText(mlist.get(position).getDesc());
            GlideImageLoader.LoadImage( ((SinglwImageViewHolder) holder).iv_top,mlist.get(position).getImages().get(0));             holder.itemView.setTag(mlist.get(position).getUrl());

        }else if(holder instanceof  PureTextHolder){
            ((PureTextHolder) holder).tv_date.setText(mlist.get(position).getPublishedAt().substring(0,10));
            ((PureTextHolder) holder).tv_name.setText(mlist.get(position).getWho());
            ((PureTextHolder) holder).tv_title.setText(mlist.get(position).getDesc());
            ImageFragmentAdapter adapter = new ImageFragmentAdapter(mactivity.getSupportFragmentManager(), mlist.get(position).getImages());
            ((PureTextHolder) holder).iv_vp.setAdapter(adapter);
          holder.itemView.setTag(mlist.get(position).getUrl());

        }
//
    }

    class PureTextHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_name;
        TextView tv_date;
        ViewPager iv_vp;
        public PureTextHolder(View view){
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            iv_vp = (ViewPager) view.findViewById(R.id.iv_vp);
        }
    }


    class TextHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_name;
        TextView tv_date;
        public TextHolder(View view)
        {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_date = (TextView) view.findViewById(R.id.tv_date);

        }
    }

    class SinglwImageViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_name;
        TextView tv_date;
        ImageView iv_top;
        public SinglwImageViewHolder(View view)
        {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            iv_top = (ImageView) view.findViewById(R.id.iv_top);
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(mlist.get(position).getImages() !=null&&mlist.get(position).getImages().size()<1){
            return TYPE_TEXT;
        }else if(mlist.get(position).getImages() !=null&&mlist.get(position).getImages().size()==1){
            return TYPE_SINGLE_IMAGE;
        }else if(mlist.get(position).getImages() !=null&& mlist.get(position).getImages().size()>1){
            return TYPE_PURE_TEXT;
        }else {
            return TYPE_TEXT;
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String url);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }



}
