package me.maodev.gankioproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.maodev.gankioproject.tool.LazyFragmentPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public  class TypeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,LazyFragmentPagerAdapter.Laziable{

    String type;
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerview;
//    @BindView(R.id.swiperefreshlayout)
//    SwipeRefreshLayout swipeRefreshLayout;

    //RecyclerView的adapter
    private MultipleAdapter adapter;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RetrofitInterface.RequestServes requestSerives;
    private int  currentPage  = 1;
    private String itemcount = "10";
    private ArrayList<ResultModel> modellist;
    public static TypeFragment newInstance(String dataType) {
        TypeFragment fragment = new TypeFragment();
        Bundle args =  new Bundle();
        args.putString("type", dataType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            type = getArguments().getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_type,container,false);
       // ButterKnife.bind(this,view);
        recyclerview = (RecyclerView)view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefreshlayout);
        init();
        getGankList(type,"load");
        return view;

    }

    private void init() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
       //设置布局管理器
        recyclerview.setLayoutManager(linearLayoutManager);
        //设置分割线
        //recyclerview.setItemAnimator(new DefaultItemAnimator());
//        mQuickAdapter = new MultipleAdapter(null);
//        recyclerview.setAdapter(mQuickAdapter);

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .build();
        //这里采用的是Java的动态代理模式
        requestSerives = retrofit.create(RetrofitInterface.RequestServes.class);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerview.addOnScrollListener(new LoadRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                currentPage++;
                getGankList(type,"add");
            }
        });
    }

    public void  getGankList(String type, final String way) {
        swipeRefreshLayout.setRefreshing(true);
        Call<ApiDate> call;
       switch (type){
           case "android":
              call = requestSerives.getApiDate("Android",itemcount,currentPage);//传入我们请求的键值对的值
               break;
           case "ios":
                call = requestSerives.getApiDate("iOS",itemcount,currentPage);//传入我们请求的键值对的值
               break;
           case "ui":
               call = requestSerives.getApiDate("前端",itemcount,currentPage);//传入我们请求的键值对的值
               break;
           default:
               call = requestSerives.getApiDate("Android",itemcount,currentPage);//传入我们请求的键值对的值
               break;

       }
        call.enqueue(new Callback<ApiDate>() {

            @Override
            public void onResponse(Call<ApiDate> call, Response<ApiDate> response) {
                System.out.println(response.body().getResults());
                if(way.equals("load")){
                    modellist = response.body().getResults();
                    adapter = new MultipleAdapter(getActivity(), GenerApplication.getInstance(),modellist);
                    recyclerview.setAdapter(adapter);
                }else if(way.equals("add")){
                    ArrayList<ResultModel> temp = response.body().getResults();
                    modellist.addAll(temp);
                    adapter.notifyDataSetChanged();
                }else {
                    ArrayList<ResultModel> temp = response.body().getResults();
                    modellist.addAll(temp);
                    adapter.notifyDataSetChanged();
                }
                adapter.setOnItemClickListener(new MultipleAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, String data) {
                        Intent intent = new Intent(getActivity(),DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",data);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ApiDate> call, Throwable t) {
                System.out.println("");
            }
        });

    }

    //刷新
    @Override
    public void onRefresh() {
        modellist.clear();
        currentPage = 1;
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(true);
        getGankList(type,"refresh");
    }
    //    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if(isVisibleToUser){
//           getGankList(type);
//        }
//        super.setUserVisibleHint(isVisibleToUser);
//    }




}
