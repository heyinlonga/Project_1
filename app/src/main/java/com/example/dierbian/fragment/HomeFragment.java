package com.example.dierbian.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.dierbian.R;
import com.example.dierbian.adapter.MyAdapter;
import com.example.dierbian.bean.BannerBeans;
import com.example.dierbian.contract.HomeContract;
import com.example.dierbian.presenter.HomePresenter;
import com.example.mvplibrary.base.BaseFragment;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.utils.URLConstant;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<HomePresenter>implements HomeContract.IHomeView {

    private RecyclerView mRlv;
    private ArrayList<BannerBeans> mBan;
    private VirtualLayoutManager layoutManager;
    private MyAdapter myAdapter;

    @Override
    protected void initView(View view) {
        //todo 数据源
        mBan = new ArrayList<>();

        //todo 找控件 别的操作
        mRlv = view.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        layoutManager = new VirtualLayoutManager(getContext());
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        mRlv.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0,15);


        myAdapter = initTile();
        




    }

    private MyAdapter initTile() {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(3);//todo 设置Item个数
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        myAdapter = new MyAdapter(singleLayoutHelper);
        return myAdapter;

    }

    @Override
    protected void initData() {
        presenter.Home(URLConstant.NEWLIST);
    }



    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public <T> void HomeRelt(T t) {

    }

    @Override
    public void tips(String string) {

    }
}
